package com.swufe.scoresforapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MajorScore {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    private String school;
    private String majorFirst;
    private String majorSecond;
    private int score;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajorFirst() {
        return majorFirst;
    }

    public void setMajorFirst(String majorFirst) {
        this.majorFirst = majorFirst;
    }

    public String getMajorSecond() {
        return majorSecond;
    }

    public void setMajorSecond(String majorSecond) {
        this.majorSecond = majorSecond;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "MajorScore{" +
                "_id=" + _id +
                ", school='" + school + '\'' +
                ", majorFirst='" + majorFirst + '\'' +
                ", majorSecond='" + majorSecond + '\'' +
                ", score=" + score +
                '}';
    }
}
