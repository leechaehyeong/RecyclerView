package com.example.recyclerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//데이터
class ItemData {

    String name;
    String amount;
    String date;
    int resourceId;

    public ItemData(int resourceId, String name, String amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
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

