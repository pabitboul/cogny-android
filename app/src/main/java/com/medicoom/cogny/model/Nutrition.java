package com.medicoom.cogny.model;

import java.util.List;

public class Nutrition {
    private String desc;
    private String calories;
    private String dailyValue;
    private List<ItemValue> fat;
    private List<ItemValue> carbs;
    private List<ItemValue> values;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(String dailyValue) {
        this.dailyValue = dailyValue;
    }

    public List<ItemValue> getFat() {
        return fat;
    }

    public void setFat(List<ItemValue> fat) {
        this.fat = fat;
    }

    public List<ItemValue> getCarbs() {
        return carbs;
    }

    public void setCarbs(List<ItemValue> carbs) {
        this.carbs = carbs;
    }

    public List<ItemValue> getValues() {
        return values;
    }

    public void setValues(List<ItemValue> values) {
        this.values = values;
    }
}
