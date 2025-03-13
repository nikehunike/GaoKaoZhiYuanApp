package com.swufe.scoresforapp.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.swufe.scoresforapp.entities.student;

import java.util.List;
@Dao
public interface StudentDao {
    @Insert
    void insert(student... students);

    @Delete
    void delete(student... students);

    @Query("DELETE FROM student")//删除整张表中的信息
    void deleteAll();
    @Update
    int update(student... student);

    @Query("select*FROM student")
    List<student> queryAll();
    
    @Query("select * FROM student WHERE phonenumber = :phonenumber ORDER BY _id desc limit 1")
    student queryByName(String phonenumber);
}
