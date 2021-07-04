package me.jinxinyu.caltracker.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.gson.Gson;
import me.jinxinyu.caltracker.dao.AuthsDAO;
import me.jinxinyu.caltracker.net.DBRemoteException;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import static javax.mail.Message.RecipientType.TO;

public class ServiceImpl {
    public class EmailConfig {
        public String sender;
        public String password;
    }

    public static String validateToken(String token, String alias) {
        long diff = 72000000L;   // Tokens valid for one hour
        long newTokenTime = 3000000L; //after 50 minutes we generate a new token

        if (token == null || token.isEmpty()) {
            //TODO: is runtime exception a good practice?
            throw new RuntimeException("401");
        }


        try {
            AuthsDAO authsDAO = new AuthsDAO();
            Map.Entry<String, String> resp = authsDAO.getToken(token);
            if (resp == null || resp.getKey() == null || resp.getValue() == null) {
                throw new RuntimeException("401");
            }

            if (!resp.getKey().equals(alias)) throw new RuntimeException("401");

            long timestamp = Long.parseLong(resp.getValue());
            long curr_time = new Timestamp(System.currentTimeMillis()).getTime();


            if (curr_time - timestamp > newTokenTime && curr_time - timestamp < diff) {
                String newToken = UUID.randomUUID().toString();
                long new_curr_time = new Timestamp(System.currentTimeMillis()).getTime();
                authsDAO.deleteToken(token);
                authsDAO.addToken(newToken, new_curr_time, alias);
                return newToken;
            }

            if (curr_time - timestamp > diff) {
                System.out.println("invalid token: the token doesn't exist");

                // logout user
                authsDAO.deleteToken(token);

                throw new RuntimeException("401");
            }
        } catch (DBRemoteException e) {
            throw new RuntimeException(e.getMessage());

        }
        return token;
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

    public static String uploadImage(String imageString, String alias) throws IOException {

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

    /** Email Service */
    private static final String HOST_CONFIG = "mail.smtp.host";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT_CONFIG = "mail.smtp.port";
    private static final String PORT = "465";
    private static final String SSL_CONFIG = "mail.smtp.ssl.enable";
    private static final String AUTH_CONFIG = "mail.smtp.auth";

    private static final String CONFIG_PATH = "server/src/main/resources/emailConfig.json";

    public static void sendEmail(String to, String subject, String body) throws MessagingException, FileNotFoundException {
        Gson parser = new Gson();
        EmailConfig config = parser.fromJson(new FileReader(CONFIG_PATH), ForgetPasswordServiceImpl.EmailConfig.class);

        final String from = config.sender;
        final String key = config.password;

        // set up mail server
        Properties properties = System.getProperties();
        properties.put(HOST_CONFIG, HOST);
        properties.put(PORT_CONFIG, PORT);
        properties.put(SSL_CONFIG, "true");
        properties.put(AUTH_CONFIG, "true");


        // Get the session object
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = from;
                String password = key;
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(TO, new InternetAddress(to));

        // Set email content
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
}
