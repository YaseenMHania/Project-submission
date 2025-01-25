package com.example.coursehubmanager;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(primaryKeys = {"userId", "courseId"})
public class Bookmark {
    private int userId; // ID المستخدم
    private int courseId; // ID الدورة

    // Constructor
    public Bookmark(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    // Default Constructor
    public Bookmark() {}

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}