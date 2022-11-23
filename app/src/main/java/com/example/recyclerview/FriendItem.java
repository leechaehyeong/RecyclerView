package com.example.recyclerview;

public class FriendItem {
    String name;
    String amount;
    String date;
    int resourceId;

    public FriendItem(int resourceId, String name, String amount, String date) {
        this.name = name;
        this.amount= amount;
        this.date= date;
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
