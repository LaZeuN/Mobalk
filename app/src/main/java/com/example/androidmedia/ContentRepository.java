package com.example.androidmedia;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContentRepository {

    private ContentDAO dao;
    private LiveData<List<Content>> contents;

    ContentRepository(Application application) {
        ContentRoomDatabase db = ContentRoomDatabase.getInstance(application);
        dao = db.contentDAO();
        contents = dao.getContents();
    }

    public LiveData<List<Content>> getAllContents() {
        return contents;
    }

    public void insert(Content content) {
        new Insert(this.dao).execute(content);
    }

    private static class Insert extends AsyncTask<Content, Void, Void> {

        private ContentDAO mDAO;

        Insert(ContentDAO dao) {
            this.mDAO = dao;
        }

        @Override
        protected Void doInBackground(Content... contents) {
            mDAO.insert(contents[0]);
            return null;
        }
    }
}
