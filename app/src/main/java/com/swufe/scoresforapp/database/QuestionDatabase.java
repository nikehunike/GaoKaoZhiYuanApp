package com.swufe.scoresforapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.swufe.scoresforapp.Dao.QuestionDao;
import com.swufe.scoresforapp.entities.Question;

@Database(entities = {Question.class},version = 3,exportSchema = true)
public  abstract class QuestionDatabase  extends RoomDatabase {
    public abstract QuestionDao questionDao();

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // 1. 创建新表
            database.execSQL(
                    "CREATE TABLE Question_new (" +
                            "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                            "questionText TEXT," +
                            "optionA TEXT," +
                            "optionB TEXT," +
                            "selecteda TEXT," +  // 新列名
                            "selectedb TEXT)"
            );

            // 2. 迁移数据
            database.execSQL(
                    "INSERT INTO Question_new (_id, questionText, optionA, optionB, selecteda, selectedb) " +
                            "SELECT _id, questionText, optionA, optionB, selecteda, selectedb " +  // 旧列名 selecteda
                            "FROM Question"
            );

            // 3. 删除旧表
            database.execSQL("DROP TABLE Question");

            // 4. 重命名新表
            database.execSQL("ALTER TABLE Question_new RENAME TO Question");
        }
    };

}


