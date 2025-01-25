package com.example.coursehubmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {
        User.class,
        Lesson.class,
        Enrollment.class,
        Course.class,
        Category.class,
        Bookmark.class
        },version = 5,exportSchema = false)
public abstract class DatabaseClass extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract  LessonDao lessonDao();
    public abstract EnrollmentDao enrollmentDao();
    public abstract CourseDao courseDao();
    public abstract CategoryDao categoryDao();
    public abstract BookmarkDao bookmarkDao();


    public static volatile DatabaseClass INSTANCE;
    public static DatabaseClass getDataBase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseClass.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseClass.class, "app_dataBase")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    db.execSQL("PRAGMA foreign_keys = ON;"); // تفعيل القيود الأجنبية
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
