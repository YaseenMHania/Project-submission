package com.example.coursehubmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.coursehubmanager.databinding.ActivityLogInBinding;

import java.util.List;

public class LogInActivity extends AppCompatActivity {
ActivityLogInBinding binding;
    String email;
    String password;
DatabaseClass db;
List<Category> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());

        SharedPreferences sharedPreferences = getSharedPreferences("saveLogin", MODE_PRIVATE);
        boolean b = sharedPreferences.getBoolean("login", false);
        String name = sharedPreferences.getString("name", "");
        String passwoed = sharedPreferences.getString("passwoed", "");
        if (b) {
            Intent intent = new Intent(LogInActivity.this, CategoryActivity.class);
            startActivity(intent);
        }

        setContentView(binding.getRoot());

        db = DatabaseClass.getDataBase(this);

        binding.teRegister.setOnClickListener(view ->
                startActivity(new Intent(LogInActivity.this, RegisterActivity.class)));

        binding.dtnLogin.setOnClickListener(view -> {
            email = binding.edLoginEmil.getText().toString().trim();
            password = binding.edLoginPassword.getText().toString().trim();
            boolean valid = true;

            if (email.isEmpty()) {
                binding.edLoginEmil.setError("يجب ألا يكون الحقل فارغًا");
                valid = false;
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edLoginEmil.setError("البريد الإلكتروني غير صالح");
                valid = false;
            }

            if (password.isEmpty()) {
                binding.edLoginPassword.setError("يجب ألا يكون الحقل فارغًا");
                valid = false;
            }
                if(valid){
                    if (email.equals("admin@gmail.com") && password.equals("admin")) {
                        Toast.makeText(LogInActivity.this, "تم تسجيل الدخول كمشرف", Toast.LENGTH_SHORT).show();
                        // تمرير قيمة isAdmin كمشرف
                        CategoryAdabter adabter = new CategoryAdabter(this,categoryList,true);

                        Intent intent = new Intent(LogInActivity.this, CategoryAdmainAcyivity.class);
                        intent.putExtra("isAdmin", true);
                        startActivity(intent);
                    } else {
                        User user = db.userDao().loginUser(email, password);
                        if (user != null) {
                            Toast.makeText(LogInActivity.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
                            CategoryAdabter adabter = new CategoryAdabter(this,categoryList,false);

                            Intent intent = new Intent(LogInActivity.this, CategoryActivity.class);
                            intent.putExtra("isAdmin", false);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LogInActivity.this, "خطأ في البريد الإلكتروني أو كلمة المرور", Toast.LENGTH_SHORT).show();
                        }
                    }


                }

                // طباعة جميع المستخدمين في قاعدة البيانات لمراجعة البيانات
                List<User> users = db.userDao().getAllUsers();
                for (User u : users) {
                    Log.d("DatabaseCheck", "Email: " + u.getEmail() + ", Password: " + u.getPassword());
                }

        });


        binding.checkedSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences chPreferences = getSharedPreferences("saveLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = chPreferences.edit();
                editor.putBoolean("login", b);

                editor.apply();
            }
        });




    }
}