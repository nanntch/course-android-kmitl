package com.kmitl.natcha58070069.roomdemo;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by student on 11/3/2017 AD.
 */

@Entity(tableName = "MESSAGE_INFO") //กำหนดชื่อได้ ถ้าไม่กำหนดมันจะเอาdefaultไปใช้
class MessageInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "TEXT")
    private String text;

    @ColumnInfo(name = "TIME")
    private String time;

    @Override
    public String toString() {
        return String.format("%s :: %s", text, time);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
