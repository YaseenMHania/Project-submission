package com.example.coursehubmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.coursehubmanager.databinding.ActivityCourseFragmentBinding;

import java.util.List;

public class CourseFragment extends Fragment {
    private ActivityCourseFragmentBinding binding;
    private List<Course> courseList;
    private CourseAdabter adapter;
    private DatabaseClass db;

    public CourseFragment() {
        // Required empty public constructor
    }

    public static CourseFragment newInstance(int categoryId) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putInt("categoryId", categoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ActivityCourseFragmentBinding.inflate(inflater, container, false);
        db = DatabaseClass.getDataBase(requireContext());

        int categoryId = getArguments() != null ? getArguments().getInt("categoryId") : -1;

        // Load courses by category

        courseList = db.courseDao().getCoursesByCategoryId(categoryId);


        adapter.coursesList = courseList;
        adapter = new CourseAdabter(courseList);
        binding.recyclerViewCourses.setAdapter(adapter);
        binding.recyclerViewCourses.setLayoutManager(new LinearLayoutManager(requireContext()));

        return binding.getRoot();
    }
}
