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
import com.example.hiddenbeyondofficial.view.activity.PlayVideoActivity;
import com.example.hiddenbeyondofficial.model.Videos;
import com.example.hiddenbeyondofficial.util.Const;

import java.util.List;

public class SearchVideoAdapter extends RecyclerView.Adapter<SearchVideoAdapter.SearchViewHolder> {
    private List<Videos> videos;
    private onClickListener onClickListener;

    public void setVideos(List<Videos> videos) {
        this.videos = videos;
    }

    public void setOnClickListener(onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(holder.itemView.getContext()).load(videos.get(position).getImage()).into(holder.videoImage);
        holder.videoName.setText(videos.get(position).getName());
        holder.playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), PlayVideoActivity.class);
                i.putExtra(Const.Sender.url, videos.get(position).getVideo());
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void filterList(List<Videos> filterList){
        videos = filterList;
        notifyDataSetChanged();
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView videoImage, playVideo;
        private TextView videoName;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            videoImage = itemView.findViewById(R.id.item_image);
            videoName = itemView.findViewById(R.id.item_name);
            playVideo = itemView.findViewById(R.id.play_video);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onClickListener != null){
                onClickListener.onSearchClick(getAdapterPosition(), view);
            }
        }
    }

    public interface onClickListener{
        void onSearchClick(int position, View view);
    }
}
