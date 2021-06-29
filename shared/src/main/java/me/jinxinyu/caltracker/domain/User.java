package me.jinxinyu.caltracker.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a user in the system.
 */
public class User implements Comparable<User>, Serializable {

    private String firstName;
    private String lastName;
    private String userId;
    private String imageUrl;
    private int weight;
    private int height;
    private int age;

    /**
     * Allows construction of the object from Json. Private so it won't be called by other code.
     */
    public User() {}

    public User(String firstName, String lastName, String userId, String imageURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.imageUrl = imageURL;
    }

    public User(String firstName, String lastName, String userId, String imageUrl,
                int weight, int height, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", alias='" + userId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public int compareTo(User user) {
        return this.getUserId().compareTo(user.getUserId());
    }
}
