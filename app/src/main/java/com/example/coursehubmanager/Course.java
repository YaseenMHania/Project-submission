package com.example.coursehubmanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table", foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "categoryId"))
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private double price;
    private String instructor;
    private String courseImage;
    private int numberOfStudents;
    private int hours;
    @ColumnInfo(name = "categoryId")
    private int categoryId;

    // Constructor
    public Course(int id, String title, String description, double price, String instructor, String courseImage, int numberOfStudents, int hours) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.instructor = instructor;
        this.courseImage = courseImage;
        this.numberOfStudents = numberOfStudents;
        this.hours = hours;
    }

    public Course(String description, String title, double price, String instructor, int numberOfStudents, int hours,int categoryId) {
        this.description = description;
        this.title = title;
        this.price = price;
        this.instructor = instructor;
        this.numberOfStudents = numberOfStudents;
        this.hours = hours;
        this.categoryId=categoryId;
    }

    // Default Constructor
    public Course() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}