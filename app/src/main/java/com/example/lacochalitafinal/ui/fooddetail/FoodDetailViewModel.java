package com.example.lacochalitafinal.ui.fooddetail;

import com.example.lacochalitafinal.Common.Common;
import com.example.lacochalitafinal.Model.CommentModel;
import com.example.lacochalitafinal.Model.FoodModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FoodDetailViewModel extends ViewModel {

    private MutableLiveData<FoodModel> mutableLiveDataFood;
    private MutableLiveData<CommentModel> mutableLiveDataComment;

    public void setCommentModel(CommentModel commentModel){
        if (mutableLiveDataFood != null)
            mutableLiveDataComment.setValue(commentModel);
    }

    public MutableLiveData<CommentModel> getMutableLiveDataComment() {
        return mutableLiveDataComment;
    }

    public FoodDetailViewModel() {
        mutableLiveDataComment = new MutableLiveData<>();
    }

    public MutableLiveData<FoodModel> getMutableLiveDataFood() {
        if (mutableLiveDataFood == null)
            mutableLiveDataFood = new MutableLiveData<>();
        mutableLiveDataFood.setValue(Common.selectedFood);
        return mutableLiveDataFood;
    }

    public void setFoodModel(FoodModel foodModel) {
        if (mutableLiveDataFood !=null)
            mutableLiveDataFood.setValue(foodModel);
    }
}