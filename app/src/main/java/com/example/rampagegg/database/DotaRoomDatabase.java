package com.example.rampagegg.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.rampagegg.model.Hero;
import com.example.rampagegg.model.Item;
import com.example.rampagegg.model.heroesstats.HeroesStats;
import com.example.rampagegg.model.matches.Matches;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Hero.class, Item.class, Matches.class, HeroesStats.class}, version = 1, exportSchema = false)
@TypeConverters({DataConverter.class})
public abstract class DotaRoomDatabase extends RoomDatabase {

    public abstract DotaDao dotaDao();

    private static DotaRoomDatabase instance;
    private static final String DATABASE_NAME = "DotaDB";
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public synchronized static DotaRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , DotaRoomDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
