package com.swufe.scoresforapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class student {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer _id;
    private String name;
    private String phonenumber;
    private Integer score;
    private String password;
    private String province;
    private String selection1;
    private String selection2;
    private String selection3;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSelection1() {
        return selection1;
    }

    public void setSelection1(String selection1) {
        this.selection1 = selection1;
    }

    public String getSelection2() {
        return selection2;
    }

    public void setSelection2(String selection2) {
        this.selection2 = selection2;
    }

    public String getSelection3() {
        return selection3;
    }

    public void setSelection3(String selection3) {
        this.selection3 = selection3;
    }

    @Override
    public String toString() {
        return "student{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", score=" + score +
                ", password='" + password + '\'' +
                ", province='" + province + '\'' +
                ", selection1='" + selection1 + '\'' +
                ", selection2='" + selection2 + '\'' +
                ", selection3='" + selection3 + '\'' +
                '}';
    }
}
