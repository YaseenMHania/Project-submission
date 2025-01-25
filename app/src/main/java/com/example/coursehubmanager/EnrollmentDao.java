package com.example.coursehubmanager;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EnrollmentDao {

    @Insert
    void insertEnrollment(Enrollment enrollment);

    @Update
    void updateEnrollment(Enrollment enrollment);

    @Delete
    void deleteEnrollment(Enrollment enrollment);

    @Query("SELECT * FROM enrollment_table WHERE userId = :userId")
    List<Enrollment> getEnrollmentsByUserId(int userId);

    @Query("SELECT * FROM enrollment_table WHERE courseId = :courseId")
    List<Enrollment> getEnrollmentsByCourseId(int courseId);

    @Query("SELECT * FROM enrollment_table WHERE userId = :userId AND courseId = :courseId")
    Enrollment getEnrollment(int userId, int courseId);
}
