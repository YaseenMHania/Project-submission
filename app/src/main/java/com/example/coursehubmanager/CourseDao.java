package com.example.coursehubmanager;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    long insertCourse(Course course);

    @Update
    int updateCourse(Course course);

    @Delete
    int deleteCourse(Course course);

    @Query("SELECT * FROM course_table WHERE id = :courseId")
    Course getCourseById(int courseId);

    @Query("SELECT * FROM course_table")
    List<Course> getAllCourses();


    @Query("SELECT * FROM course_table WHERE categoryId = :categoryId")
    List<Course> getCoursesByCategoryId(int categoryId);

    @Query("SELECT * FROM course_table WHERE title LIKE '%' || :keyword || '%'")
    List<Course> searchCoursesByTitle(String keyword);
}
