package com.mahtab.jokes.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = EntityValue.class, version = 1,exportSchema = false)
public abstract class JokeListDB extends RoomDatabase {

    private static JokeListDB INSTANCE;

    public abstract JokeListDao jokeListDao();

    public static JokeListDB getJokeListDb(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), JokeListDB.class, "joke-database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}
