package com.example.lacochalitafinal.Callback;

import com.example.lacochalitafinal.Model.CategoryModel;

import java.util.List;

public interface ICategoryCallbackListener {
    void onCategoryLoadSuccess(List<CategoryModel> categoryModels);
    void onCategorylLoadFailed(String message);
}
