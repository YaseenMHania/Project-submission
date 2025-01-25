package com.example.coursehubmanager;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "enrollment_table", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"),
        @ForeignKey(entity = Course.class, parentColumns = "id", childColumns = "courseId")
})
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId; // ID المستخدم
    private int courseId; // ID الدورة
    private String status; // الحالة: ongoing, completed

    // Constructor
    public Enrollment(int id, int userId, int courseId, String status) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
    }

    // Default Constructor
    public Enrollment() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
