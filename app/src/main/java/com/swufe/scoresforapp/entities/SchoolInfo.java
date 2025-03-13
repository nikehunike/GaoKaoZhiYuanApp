package com.swufe.scoresforapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SchoolInfo {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    private String school;
    private String majorFirst;
    private String majorSecond;
}
