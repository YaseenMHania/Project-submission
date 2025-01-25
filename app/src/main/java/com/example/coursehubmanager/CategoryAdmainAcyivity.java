package com.example.coursehubmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.coursehubmanager.databinding.ActivityCategoryAdmainAcyivityBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdmainAcyivity extends AppCompatActivity {
        ActivityCategoryAdmainAcyivityBinding binding;
        DatabaseClass db;
        AlertDialog dialog;
        List<Category> categoryList;
    List<Course> courseList;
    CourseAdabter courseAdabter;

        CategoryAdabter adabter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryAdmainAcyivityBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        db = DatabaseClass.getDataBase(this);
        boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);

        // Pass isAdmin to the adapter



        // Load data from DB
        categoryList = new ArrayList<>();
        categoryList.addAll(db.categoryDao().getAllCategories());
        adabter = new CategoryAdabter(this,categoryList,isAdmin);
        binding.resCategoryAdmin.setAdapter(adabter);
        binding.resCategoryAdmin.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Refresh data when returning from AddCategoryActivity
        adabter.notifyDataSetChanged();



        courseList = new ArrayList<>();
        courseList.addAll(db.courseDao().getAllCourses());
        courseAdabter = new CourseAdabter(courseList);
        binding.resCourseAdmin.setAdapter(courseAdabter);
        binding.resCourseAdmin.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Refresh data when returning from AddCategoryActivity
        courseAdabter.notifyDataSetChanged();

        // Add button to open AddCategoryActivity
        binding.btnAdd.setOnClickListener(view -> {
            startActivity(new Intent(CategoryAdmainAcyivity.this, AddCatergoryActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the categories after returning from AddCategoryActivity
        categoryList.clear();
        categoryList.addAll(db.categoryDao().getAllCategories());
        adabter.notifyDataSetChanged();
    }
}