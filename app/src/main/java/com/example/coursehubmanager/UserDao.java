package com.example.coursehubmanager;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    long insertUser(User user);

    @Update
    int updateUser(User user);

    @Delete
    int deleteUser(User user);

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    User loginUser(String email, String password);

    @Query("SELECT * FROM user_table WHERE id = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();
}
