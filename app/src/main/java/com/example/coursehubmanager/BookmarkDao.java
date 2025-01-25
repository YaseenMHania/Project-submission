package com.example.coursehubmanager;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookmarkDao {

    @Insert
    void insertBookmark(Bookmark bookmark);

    @Delete
    void deleteBookmark(Bookmark bookmark);

    @Query("SELECT * FROM bookmark WHERE userId = :userId")
    List<Bookmark> getBookmarksByUserId(int userId);

    @Query("SELECT * FROM bookmark WHERE userId = :userId AND courseId = :courseId")
    Bookmark getBookmark(int userId, int courseId);
}
