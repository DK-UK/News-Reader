package com.developer.dk.sqlitedemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewHolder> {

    private ArrayList<NewsHolder> newsList;
    private Context context;

    public NewsAdapter(Context context,ArrayList<NewsHolder> newsList){
        this.context = context;
        this.newsList = newsList;
    }
    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView textViewNewsTitle;
        LinearLayout newsLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNewsTitle = itemView.findViewById(R.id.textViewNews);
            newsLayout = itemView.findViewById(R.id.newsLayout);
        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NewsHolder currentItem = newsList.get(position);

        holder.textViewNewsTitle.setText(currentItem.getNewsTitle());

        holder.newsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,NewsActivity.class);
                i.putExtra("articleUrl",currentItem.getNewsUrl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
