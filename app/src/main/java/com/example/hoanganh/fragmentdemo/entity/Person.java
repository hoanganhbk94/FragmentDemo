package com.example.hoanganh.fragmentdemo.entity;

import java.io.Serializable;

/**
 * Created by HoangAnh on 3/20/2016.
 */
public class Person implements Serializable{
    // Fields
    private String name;
    private String birthDay;
    private boolean sex;

    // Constructor
    public Person() {
    }

    public Person(String name, String birthDay, boolean sex) {
        this.name = name;
        this.birthDay = birthDay;
        this.sex = sex;
    }

    // Getter, setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
