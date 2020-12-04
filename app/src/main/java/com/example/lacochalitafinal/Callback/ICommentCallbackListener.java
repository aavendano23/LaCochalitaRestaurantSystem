package com.example.lacochalitafinal.Callback;

import com.example.lacochalitafinal.Model.CommentModel;

import java.util.List;

public interface ICommentCallbackListener {
    void onCommentLoadSuccess(List<CommentModel> commentModels);
    void onCommentLoadFailed(String message);


}
