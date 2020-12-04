package com.example.lacochalitafinal.Adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.lacochalitafinal.Model.CommentModel;
import com.example.lacochalitafinal.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.MyViewHolder> {

    Context context;
    List<CommentModel> commentModelsList;

    public MyCommentAdapter(Context context, List<CommentModel> commentModelsList) {
        this.context = context;
        this.commentModelsList = commentModelsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.layout_comment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Long timeStamp = Long.valueOf(commentModelsList.get(position).getCommentTimeStamp().get("timeStamp").toString());
        holder.txt_comment_date.setText(DateUtils.getRelativeTimeSpanString(timeStamp));
        holder.txt_comment_name.setText(commentModelsList.get(position).getName());
        holder.txt_comment.setText(commentModelsList.get(position).getComment());
        holder.rating_bar_comment.setRating(commentModelsList.get(position).getRatingValue());

    }

    @Override
    public int getItemCount() {
        return commentModelsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private Unbinder unbinder;

        @BindView(R.id.txt_comment_name)
        TextView txt_comment_name;
        @BindView(R.id.txt_comment_date)
        TextView txt_comment_date;
        @BindView(R.id.txt_comment)
        TextView txt_comment;
        @BindView(R.id.rating_bar_comment)
        RatingBar rating_bar_comment;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);

        }
    }
}
