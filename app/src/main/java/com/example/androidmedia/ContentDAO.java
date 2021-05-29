package com.example.androidmedia;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContentDAO {

    @Insert
    void insert(Content content);

    @Query("DELETE FROM content_table")
    void deleteAll();

//    void delete(Content content);

    @Query("SELECT * FROM content_table ORDER BY identifier")
    LiveData<List<Content>> getContents();

}
