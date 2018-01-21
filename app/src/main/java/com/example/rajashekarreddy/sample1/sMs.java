package com.example.rajashekarreddy.sample1;


import android.support.annotation.NonNull;

import java.util.Date;

public class sMs implements Comparable<sMs> {

    private String contact;
    private String msg;
    private Date date;

    public sMs(String contact, String msg, Date date) {
        if(contact.length()==10){
            this.contact="+91".concat(contact);
        }else {
            this.contact = contact;
        }
        this.msg = msg;
        this.date = date;
    }

    public sMs(Long contact, String msg, Date date) {
            this.contact = contact.toString();

        this.msg = msg;
        this.date = date;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(@NonNull sMs s) {
        return getDate().compareTo(s.getDate());

    }
}
