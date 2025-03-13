package com.swufe.scoresforapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.swufe.scoresforapp.entities.MajorScore;

import java.util.List;
@Dao
public interface MajorScoreDao {
    @Insert
    void insert(MajorScore majorScores);

    @Transaction
    @Query("DELETE FROM MajorScore")//删除整张表中的信息
    void deleteAll();
//    @Query("DELETE FROM Question WHERE questionText = :question")
//    int deleteByQuestionText(String question);
    @Update
    int update(MajorScore... majorScores);

    @Query("select*FROM MajorScore")
    List<MajorScore> queryAll();

    @Query("SELECT*FROM MajorScore WHERE school = :school ORDER BY _id desc limit 1")
    MajorScore queryBySchool(String school);
}
