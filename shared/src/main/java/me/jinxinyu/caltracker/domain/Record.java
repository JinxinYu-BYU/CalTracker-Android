package me.jinxinyu.caltracker.domain;

public class Record {
    private String userId;
    private String foodName;
    private int calories;
    private long time;
    //optional
    private String foodImageURL;

    public Record(String userId, String foodName, int calories, long time) {
        this.userId = userId;
        this.foodName = foodName;
        this.calories = calories;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getFoodImageURL() {
        return foodImageURL;
    }

    public void setFoodImageURL(String foodImageURL) {
        this.foodImageURL = foodImageURL;
    }
}
