package com.example.coursehubmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.coursehubmanager.databinding.ActivityAddCatergoryBinding;

import java.util.ArrayList;
import java.util.List;

public class AddCatergoryActivity extends AppCompatActivity {
ActivityAddCatergoryBinding binding;
DatabaseClass db;
    CategoryAdabter adabter;
    List<Category> categoryList;
    List<Course> courseList;
    CourseAdabter courseAdabter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCatergoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = DatabaseClass.getDataBase(this);

        binding.btnAdd.setOnClickListener(view -> {
            String name = binding.edNameCateg.getText().toString();
            if (name.isEmpty()){
                binding.edNameCateg.setError("لايمكن ان يكون فارغ");
            }else{
                Category category = new Category(name);
              long id =   db.categoryDao().insertCategory(category);
              category.setId((int)id);
                if (id != -1) {
                    Log.d("AddCategoryActivity", "Category added successfully with ID: " + id);
                    category.setId((int) id);
                    startActivity(new Intent(AddCatergoryActivity.this, CategoryAdmainAcyivity.class));
                } else {
                    Log.e("AddCategoryActivity", "Failed to add category");
                }

            }
        });



    }


}