package com.swufe.scoresforapp.entities;

import android.support.annotation.NonNull;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Question {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private  int _id;
    private String questionText;
    private String optionA;
    private String optionB;
    @ColumnInfo(name = "selecteda")
    private String selecteda;
    @ColumnInfo(name = "selectedb")
    private String selectedb;

    public Question()
    {

    }

    @Ignore
    public Question(String questionText, String optionA, String optionB, String selecteda, String selectedb) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.selecteda = selecteda;
        this.selectedb = selectedb;
    }
    @Ignore
    public Question(String questionText, String optionA, String optionB) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
    }

    public String getSelecteda() {
        return selecteda;
    }

    public void setSelecteda(String selecteda) {
        this.selecteda = selecteda;
    }

    public String getSelectedb() {
        return selectedb;
    }

    public void setSelectedb(String selectedb) {
        this.selectedb = selectedb;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }



    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "_id=" + _id +
                ", questionText='" + questionText + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", selecteda='" + selecteda + '\'' +
                ", selectedb='" + selectedb + '\'' +
                '}';
    }
}
