package com.example.coursehubmanager;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "lesson_table", foreignKeys = @ForeignKey(entity = Course.class, parentColumns = "id", childColumns = "courseId"))
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int courseId;
    private String title;
    private String description;
    private String videoLink;
    private boolean isCompleted; // حالة الدرس (مكتمل أم لا)

    // Constructor
    public Lesson(int id, int courseId, String title, String description, String videoLink, boolean isCompleted) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.videoLink = videoLink;
        this.isCompleted = isCompleted;
    }

    // Default Constructor
    public Lesson() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
