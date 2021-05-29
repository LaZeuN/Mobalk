package com.example.androidmedia;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContentViewModel extends AndroidViewModel {

    private ContentRepository repository;
    private LiveData<List<Content>> contents;

    public ContentViewModel(Application application) {
        super(application);

        this.repository = new ContentRepository(application);
        this.contents = repository.getAllContents();
    }

    public LiveData<List<Content>> getAllContents() {
        return this.contents;
    }

    public void insert(Content content) {
        this.repository.insert(content);
    }
}
