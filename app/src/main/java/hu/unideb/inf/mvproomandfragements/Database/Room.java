package hu.unideb.inf.mvproomandfragements.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import hu.unideb.inf.mvproomandfragements.Database.DAOinterfaces.PersonEntityDAO;
import hu.unideb.inf.mvproomandfragements.Database.Models.PersonEntity;

@Database(entities = {PersonEntity.class}, version = 1, exportSchema = false)
public abstract class Room extends RoomDatabase {

    private static Room INSTANCE;

    public static Room getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = androidx.room.Room.databaseBuilder(context.getApplicationContext(), Room.class, "Persons")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static Room getInstance() {
        if(INSTANCE == null) return null;

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract PersonEntityDAO personEntityDAO();
}
