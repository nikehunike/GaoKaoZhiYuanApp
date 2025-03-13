package com.swufe.scoresforapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.swufe.scoresforapp.Dao.MajorScoreDao;
import com.swufe.scoresforapp.entities.MajorScore;

@Database(entities = {MajorScore.class},version = 1,exportSchema = false)
public abstract class MajorDatabase extends RoomDatabase {
    public abstract MajorScoreDao majorScoreDao();

}
