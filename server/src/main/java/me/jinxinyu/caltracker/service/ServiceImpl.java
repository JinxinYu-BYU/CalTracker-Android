package me.jinxinyu.caltracker.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import me.jinxinyu.caltracker.dao.AuthsDAO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;

public class ServiceImpl {
    public void validateToken(String token) {
        long diff = 72000000L;   // Tokens valid for one hour

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("401");
        }

        AuthsDAO authsDAO = new AuthsDAO();
        String resp = authsDAO.getToken(token);
        if (resp == null || resp.isEmpty()) {
            throw new RuntimeException("401");
        }

        long timestamp = Long.parseLong(resp);

        long curr_time = new Timestamp(System.currentTimeMillis()).getTime();

        if (curr_time - timestamp > diff) {
            System.out.println("invalid token: the token doesn't exist");
            throw new RuntimeException("401");
        }
    }

    public static String generateHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("501");
        }
    }

    public String uploadImage(String imageString, String alias) throws IOException {

        byte[] image = Base64.getDecoder().decode(imageString);

        AmazonS3Client s3Client = (AmazonS3Client) AmazonS3ClientBuilder.defaultClient();

        String file_name = alias + ".jpg";
        System.out.println("fileName:" + file_name);
        String bucket_name = "jinxinyucs340/profile-pictures";

        InputStream stream = new ByteArrayInputStream(image);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(image.length);
        meta.setContentType("image/png");

        s3Client.putObject(new PutObjectRequest(bucket_name, file_name,
                stream, meta)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        String url = s3Client.getResourceUrl(bucket_name, file_name);
        stream.close();

        return url;
    }
}
