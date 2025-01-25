package com.example.coursehubmanager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private CourseRepository repository;
    private List<Course> allCourses;

    public CourseViewModel(Application application) {
        super(application);
        repository = new CourseRepository(application);
        allCourses = repository.getAllCourses();


    }

    // استرجاع كل الدورات
    public List<Course> getAllCourses() {
        return allCourses;
    }

    // إضافة دورة جديدة
    public void addCourse(Course course) {
        repository.insertCourse(course);
    }

    // حذف دورة
    public void removeCourse(Course course) {
        repository.deleteCourse(course);
    }
}