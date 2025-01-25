package com.example.coursehubmanager;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursehubmanager.databinding.ItemCategoryBinding;
import com.example.coursehubmanager.databinding.ItemCourseBinding;

import java.util.List;

public class CourseAdabter extends RecyclerView.Adapter<CourseAdabter.CourseViewHolder> {
List<Course> coursesList;


    public CourseAdabter(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCourseBinding binding = ItemCourseBinding.inflate(inflater,parent,false);
        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = coursesList.get(position);
        holder.binding.courseTitle.setText(course.getTitle());
        holder.binding.courseDescription.setText(course.getDescription());
        holder.binding.courseInstructor.setText(course.getInstructor());
        holder.binding.courseNumberOfStudent.setText(course.getNumberOfStudents());
        holder.binding.courseCountHours.setText(course.getHours());

            Double price = course.getPrice();
        holder.binding.coursePrice.setText(price.toString());


    }

    @Override
    public int getItemCount() {
       return coursesList.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        ItemCourseBinding binding;

        public CourseViewHolder(@NonNull ItemCourseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}
