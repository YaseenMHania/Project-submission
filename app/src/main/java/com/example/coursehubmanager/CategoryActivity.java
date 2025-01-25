package com.example.coursehubmanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursehubmanager.databinding.ActivityCategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
ActivityCategoryBinding binding;
    AlertDialog dialog;
    List<Category> categoryList;
    CategoryAdabter adabter;
    DatabaseClass db;

    List<Course> courseList;
    CategoryAdabter categoryAdabter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
/*
        adabter.setOnCategoryClickListener(category -> {
            int id = category.getId();
            showCoursesFragment(id);
        });

*/



        db = DatabaseClass.getDataBase(this);
        boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);

        // Load data from DB
        categoryList = new ArrayList<>();
        categoryList.addAll(db.categoryDao().getAllCategories());
        adabter = new CategoryAdabter(this,categoryList,isAdmin);
        binding.resCategoryAdmin.setAdapter(adabter);
        binding.resCategoryAdmin.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Refresh data when returning from AddCategoryActivity
        adabter.notifyDataSetChanged();

/*
//هان بده يظهر الfragment الخاص ب الCours
        RecyclerView recyclerView = binding.resCourseAdmin;
        CourseAdabter adapter = new CourseAdabter(courseList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
*/


    }
/*
    private void showCoursesFragment(int categoryId) {
        CourseFragment courseFragment = CourseFragment.newInstance(categoryId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, courseFragment) // fragmentContainer هو ID لعنصر FrameLayout
                .addToBackStack(null)
                .commit();
    }

 */
}