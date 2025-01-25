package com.example.coursehubmanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursehubmanager.databinding.ActivityAddCatergoryBinding;
import com.example.coursehubmanager.databinding.ActivityCategoryAdmainAcyivityBinding;
import com.example.coursehubmanager.databinding.ItemCategoryBinding;


import java.util.List;
//RecyclerView.Adapter<CategoryAdabter.CategoryViewHolder


public class CategoryAdabter extends RecyclerView.Adapter<CategoryAdabter.CategoryViewHolder> {
    private List<Category> categoryList;
    private OnCategoryClickListener listener;

    Context context;
    DatabaseClass db = DatabaseClass.getDataBase(context);
    private boolean isAdmin = true;




    public CategoryAdabter(Context context, List<Category> categoryList, boolean isAdmin) {
        this.context = context;
        this.categoryList = categoryList;
        this.isAdmin = isAdmin;
        this.db = DatabaseClass.getDataBase(context);
    }

    public CategoryAdabter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCategoryBinding binding = ItemCategoryBinding.inflate(inflater, parent, false);
        return new CategoryViewHolder(binding);
    }


    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.binding.tvCategoryName.setText(category.getName());

        if (isAdmin) {
            holder.binding.btnAddCourse.setVisibility(View.VISIBLE);
            holder.binding.btnDeleteCategory.setVisibility(View.VISIBLE);

            holder.binding.btnAddCourse.setOnClickListener(view -> {
                Intent intent = new Intent(context, AddCoursuActivity.class);
                intent.putExtra("categoryId", category.getId());
                context.startActivity(intent);
            });

            holder.binding.btnDeleteCategory.setOnClickListener(view -> {
                db.categoryDao().deleteCategory(category);
                categoryList.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Category Deleted", Toast.LENGTH_SHORT).show();
            });
        } else {
            // إخفاء الأزرار للمستخدم العادي
            holder.binding.btnAddCourse.setVisibility(View.GONE);
            holder.binding.btnDeleteCategory.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onCategoryClick(categoryList.get(position));
            }
        });

}



    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void updateCategories(List<Category> newCategories) {
        this.categoryList = newCategories;
        notifyDataSetChanged();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;

        public CategoryViewHolder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;




        }


    }
    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

}

