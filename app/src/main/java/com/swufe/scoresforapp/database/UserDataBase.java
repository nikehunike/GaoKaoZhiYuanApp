package com.swufe.scoresforapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.swufe.scoresforapp.Dao.StudentDao;
import com.swufe.scoresforapp.entities.student;

//entities 表示数据库有哪些表，version表示数据库的版本号
//exportSchem表示是否导出数据库信息的json串，建议设置成false，若为true，还需给出json的存储路径，在build.gradle中给出
@Database(entities = {student.class},version = 1,exportSchema = true)
public abstract class UserDataBase extends RoomDatabase {
    public abstract StudentDao userDao();
}
