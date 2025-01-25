package com.example.coursehubmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.coursehubmanager.databinding.ActivityAddCoursuBinding;

import java.util.ArrayList;
import java.util.List;public class AddCoursuActivity extends AppCompatActivity {
    ActivityAddCoursuBinding binding;
    List<Course> courseList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    CategoryAdabter adabter;
    DatabaseClass db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCoursuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = DatabaseClass.getDataBase(this);

        // تهيئة المحول وربطه بالبيانات
        adabter = new CategoryAdabter(this, categoryList, false);

        // إضافة دورة جديدة عند النقر على الزر
        binding.btnAddCourse.setOnClickListener(view -> {
            String title = binding.edTitle.getText().toString();
            String description = binding.edDescription.getText().toString();
            String countHoursText = binding.edCountHours.getText().toString();
            String instructor = binding.edInstructor.getText().toString();
            String numberOfStudentsText = binding.edNumberOfStudent.getText().toString();
            String priceText = binding.edPrice.getText().toString();

            // الحصول على categoryId من المدخلات أو Intent
            int categoryId = getIntent().getIntExtra("CATEGORY_ID", -1); // الطريقة التي تستخدمها للحصول على الـ ID

            // التحقق إذا كانت الفئة موجودة في قاعدة البيانات
            Category category = db.categoryDao().getCategoryById(categoryId);
            if (category == null) {
                Toast.makeText(AddCoursuActivity.this, "الفئة غير موجودة في قاعدة البيانات", Toast.LENGTH_SHORT).show();
                return;
            }

            // التحقق من أن الحقول ليست فارغة
            if (title.isEmpty() || description.isEmpty() || countHoursText.isEmpty() || numberOfStudentsText.isEmpty() || priceText.isEmpty()) {
                Toast.makeText(AddCoursuActivity.this, "يرجى ملء جميع الحقول", Toast.LENGTH_SHORT).show();
                return; // توقف التنفيذ إذا كانت الحقول فارغة
            }

            // تحويل النصوص إلى الأنواع المناسبة
            int countHours = 0;
            int numberOfStudents = 0;
            double price = 0;

            try {
                countHours = Integer.parseInt(countHoursText);
                numberOfStudents = Integer.parseInt(numberOfStudentsText);
                price = Double.parseDouble(priceText.replaceAll("[^0-9\\.]", "")); // إزالة الرموز غير العددية في السعر
            } catch (NumberFormatException e) {
                Toast.makeText(AddCoursuActivity.this, "يرجى إدخال قيم صحيحة للأرقام", Toast.LENGTH_SHORT).show();
                return; // توقف التنفيذ إذا كانت القيم غير صحيحة
            }

            // إنشاء دورة جديدة
            Course course = new Course(description, title, price, instructor, numberOfStudents, countHours, categoryId);
            long id = db.courseDao().insertCourse(course);
            course.setId((int) id);

            // العودة إلى الواجهة السابقة بعد إضافة الدورة
            startActivity(new Intent(AddCoursuActivity.this, CategoryAdmainAcyivity.class));

            // إضافة الدورة إلى القائمة وتحديث العرض
            courseList.add(course);
            adabter.notifyItemInserted(courseList.size() - 1);
        });
    }
}
