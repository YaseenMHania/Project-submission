package com.example.coursehubmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.coursehubmanager.databinding.ActivityRegisterBinding;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
ActivityRegisterBinding binding;
DatabaseClass db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         db = DatabaseClass.getDataBase(this);



        binding.dtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.edEmail.getText().toString();
                String password = binding.edPassword.getText().toString();
                String usernmae = binding.edUserName.getText().toString();

                if (email.isEmpty()) {
                    binding.edEmail.setError("يجب ألا يكون الحقل فارغًا");
                }
                if (password.isEmpty()) {
                    binding.edPassword.setError("يجب ألا يكون الحقل فارغًا");
                }
                if (usernmae.isEmpty()) {
                    binding.edUserName.setError("يجب ألا يكون الحقل فارغًا");
                }
                if (!email.isEmpty() && !password.isEmpty() && !usernmae.isEmpty()) {
                    User user = new User(email, password);
                    long id = db.userDao().insertUser(user);
                    Toast.makeText(RegisterActivity.this, "تم انشاء حساب", Toast.LENGTH_SHORT).show();
                    user.setId(id);
                    startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
                    finish();
                }


            }
        });






    }
}