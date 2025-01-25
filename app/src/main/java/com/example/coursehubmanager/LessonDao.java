package com.example.coursehubmanager;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LessonDao {

    @Insert
    void insertLesson(Lesson lesson);

    @Update
    void updateLesson(Lesson lesson);

    @Delete
    void deleteLesson(Lesson lesson);

    @Query("SELECT * FROM lesson_table WHERE courseId = :courseId")
    List<Lesson> getLessonsByCourseId(int courseId);

    @Query("SELECT * FROM lesson_table WHERE id = :lessonId")
    Lesson getLessonById(int lessonId);

    @Query("SELECT * FROM lesson_table")
    List<Lesson> getAllLessons();
}
