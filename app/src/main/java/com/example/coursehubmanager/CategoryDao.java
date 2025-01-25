package com.example.coursehubmanager;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    long insertCategory(Category category);

    @Update
    int updateCategory(Category category);

    @Delete
    int deleteCategory(Category category);

    @Query("SELECT * FROM category_table WHERE id = :categoryId")
    Category getCategoryById(int categoryId);

    @Query("SELECT * FROM category_table")
    List<Category> getAllCategories();
}
