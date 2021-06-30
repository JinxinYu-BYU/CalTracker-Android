package me.jinxinyu.caltracker.domain;

public class Record {
    private String alias;
    private String foodName;
    private int calories;
    private long time;
    //optional
    private String foodImageURL;

    public Record(String alias, String foodName, int calories, long time) {
        this.alias = alias;
        this.foodName = foodName;
        this.calories = calories;
        this.time = time;
    }

    public Record(String alias, String foodName, int calories, long time, String foodImageURL) {
        this.alias = alias;
        this.foodName = foodName;
        this.calories = calories;
        this.time = time;
        this.foodImageURL = foodImageURL;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
