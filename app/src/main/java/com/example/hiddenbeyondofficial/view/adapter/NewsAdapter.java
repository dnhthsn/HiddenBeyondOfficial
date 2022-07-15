package com.example.hiddenbeyondofficial.view.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.model.News;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.view.activity.VideoDetailActivity;
import com.example.hiddenbeyondofficial.view.activity.WebNewsActivity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> news;

    public void setNews(List<News> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(holder.itemView.getContext()).load(news.get(position).getImage()).into(holder.itemNews);
        holder.itemNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), WebNewsActivity.class);
                i.putExtra(Const.Sender.contentUrl, news.get(position).getContent());
                holder.itemView.getContext().startActivity(i);
            }
        });

        holder.newsTitle.setText(news.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemNews;
        private TextView newsTitle;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            itemNews = itemView.findViewById(R.id.item_news_image);
            newsTitle = itemView.findViewById(R.id.news_title);
        }
    }
}
