package me.jinxinyu.caltracker.service.request;

import java.math.BigDecimal;

/**
 * User Register Request
 */
public final class RegisterRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String imageUrl;

    private int weight;
    private int height;
    private int age;

    /**
     * Register request constructor
     */
    public RegisterRequest(String firstName, String lastName, String userName, String password,
                          String imageUrl, int weight, int height, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.imageUrl = imageUrl;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    private RegisterRequest() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }
}

