package com.example.coursehubmanager;
import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.lifecycle.LiveData;

public class CourseRepository {
    private CourseDao courseDao;
    private List<Course> allCourses;
    private final ExecutorService executorService;


    public CourseRepository(Application application) {

    DatabaseClass database = DatabaseClass.getDataBase(application);

        courseDao = database.courseDao();
        allCourses = courseDao.getAllCourses();//ليش هان الخطا

        courseDao.getAllCourses();
        executorService = Executors.newSingleThreadExecutor();
    }

    // استرجاع كل الدورات
    public List<Course> getAllCourses() {
        return allCourses;
    }

    // إضافة دورة جديدة
    public void insertCourse(Course course) {
        executorService.execute(() -> courseDao.insertCourse(course));
    }

    // حذف دورة
    public void deleteCourse(Course course) {
        executorService.execute(() -> courseDao.deleteCourse(course));
    }
}
