package com.mp2.mvproom.database;

import android.app.Person;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mp2.mvproom.database.dao.PersonDao;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class  AppDatabase extends RoomDatabase {


    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "persons")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract PersonDao personModel();
}
