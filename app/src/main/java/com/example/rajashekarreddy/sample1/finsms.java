package com.example.rajashekarreddy.sample1;


import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.Date;

public class finsms implements Comparator<finsms> {
    private int id;
    private String contact;
    private String msg;
    private Date date;

    public finsms(int id, String contact, String msg, Date date) {
        this.id = id;
        this.contact = contact;
        this.msg = msg;
        this.date = date;
    }

    public finsms(String contact, String msg, Date date) {
        if(contact.length()==10){
            this.contact="+91".concat(contact);
        }else {
            this.contact = contact;
        }
        this.msg = msg;
        this.date = date;
    }

    public finsms(Long contact, String msg, Date date) {
        this.contact = contact.toString();

        this.msg = msg;
        this.date = date;
    }

    public finsms() {

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
    public int compare(finsms o1, finsms o2) {
        return o1.getDate().compareTo(o2.getDate());
    }

    public Object getId() {
        return id;
    }
}
