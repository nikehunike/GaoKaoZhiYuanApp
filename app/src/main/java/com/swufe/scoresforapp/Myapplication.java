package com.swufe.scoresforapp;

import static androidx.room.Room.databaseBuilder;

import static com.swufe.scoresforapp.database.QuestionDatabase.MIGRATION_2_3;

import android.app.Application;

import androidx.room.RoomDatabase;

import com.swufe.scoresforapp.database.MajorDatabase;
import com.swufe.scoresforapp.database.QuestionDatabase;
import com.swufe.scoresforapp.database.UserDataBase;

import java.util.HashMap;

public class Myapplication extends Application {

    private static Myapplication  myapp;

    //声明一个书籍数据库对象
    private UserDataBase UserDataBase;
    //声明一个公共的信息映设对象，可当作全局变量使用
    public HashMap<String,String> infoMap = new HashMap<>();
    private QuestionDatabase questionDatabase;
    private MajorDatabase majorDatabase;

    public static Myapplication getInstance(){
        return myapp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myapp = this;
        //构建数据库实例
        UserDataBase = databaseBuilder(this, UserDataBase.class,"UserInfo")
                //允许迁移数据库，Room默认删除数据库再创建新的数据库。会导致数据丢失

                .addMigrations()
                .allowMainThreadQueries()//允许在主线程中操纵数据库，Room默认不允许
                .build();
        questionDatabase = databaseBuilder(this, QuestionDatabase.class,"QuestionrInfo")
                //允许迁移数据库，Room默认删除数据库再创建新的数据库。会导致数据丢失
                //.fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_2_3)
                .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)//Room 默认启用了 WAL 模式，这会导致某些 SQLite 工具（如 SQLite Expert）无法直接读取数据库。
                .allowMainThreadQueries()//允许在主线程中操纵数据库，Room默认不允许
                .build();

        majorDatabase = databaseBuilder(this, MajorDatabase.class,"School")
                //允许迁移数据库，Room默认删除数据库再创建新的数据库。会导致数据丢失

                .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)//Room 默认启用了 WAL 模式，这会导致某些 SQLite 工具（如 SQLite Expert）无法直接读取数据库。
                .allowMainThreadQueries()//允许在主线程中操纵数据库，Room默认不允许
                .build();

    }

    public UserDataBase getBookDataBase(){
        return UserDataBase;
    }

    public QuestionDatabase getQuestionDatabase(){
        return questionDatabase;
    }
}
