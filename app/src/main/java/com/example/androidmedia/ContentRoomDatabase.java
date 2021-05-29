package com.example.androidmedia;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Content.class}, version = 1, exportSchema = false)
public abstract class ContentRoomDatabase extends RoomDatabase {

    public abstract ContentDAO contentDAO();

    private static ContentRoomDatabase instance;

    public static ContentRoomDatabase getInstance(Context context) {

        if ( instance == null) {
            synchronized (ContentRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), ContentRoomDatabase.class, "content_database").fallbackToDestructiveMigration().build();
                }
            }
        }

        return instance;
    }

    private static RoomDatabase.Callback populationalCallback = new RoomDatabase.Callback() {
        public void onOpen(@NonNull SupportSQLiteDatabase db){

        }
    };

    private static class InitDb extends AsyncTask<Void, Void, Void> {

        private ContentDAO dao;


        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
