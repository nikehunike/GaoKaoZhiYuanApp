package com.swufe.scoresforapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.swufe.scoresforapp.entities.Question;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question... questions);

    @Transaction
    @Query("DELETE FROM Question")//删除整张表中的信息
    void deleteAll();
    @Query("DELETE FROM Question WHERE questionText = :question")
    int deleteByQuestionText(String question);
    @Update
    int update(Question... questions);

    @Query("select*FROM Question")
    List<Question> queryAll();

    @Query("SELECT*FROM Question WHERE questionText = :question ORDER BY _id desc limit 1")
    Question queryByName(String question);

}
