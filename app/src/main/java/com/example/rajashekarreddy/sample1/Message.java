package com.example.rajashekarreddy.sample1;

/**
 * Created by Rajashekar Reddy on 25-Jan-18.
 */

public class Message {
    private String msg;
    private String date;
    private int id;
    private String type;

    public Message(int id,String msg, String date,String type) {
        this.msg = msg;
        this.date = date;
        this.id = id;
        this.type=type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {

        return type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getId() {
        return id;
    }
}
