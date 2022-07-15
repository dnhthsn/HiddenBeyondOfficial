package com.example.hiddenbeyondofficial.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.FragmentHomeBinding;
import com.example.hiddenbeyondofficial.databinding.FragmentSearchVideoBinding;
import com.example.hiddenbeyondofficial.view.activity.VideoDetailActivity;
import com.example.hiddenbeyondofficial.view.adapter.SearchVideoAdapter;
import com.example.hiddenbeyondofficial.control.rest.Callback;
import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.model.Videos;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.viewmodel.VideoViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearchVideo extends Fragment implements SearchVideoAdapter.onClickListener{
    private SearchVideoAdapter searchVideoAdapter;
    private List<Videos> videos;
    private List<Videos> filter;

    private FragmentSearchVideoBinding binding;
    private VideoViewModel videoViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchVideoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoViewModel = new VideoViewModel();

        videos = new ArrayList<>();
        filter = new ArrayList<>();

        videoViewModel.getVideos().observe(getViewLifecycleOwner(), new Observer<List<Videos>>() {
            @Override
            public void onChanged(List<Videos> list) {
                for (Videos video : list){
                    videos.add(video);
                }
                searchVideoAdapter.notifyDataSetChanged();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        searchVideoAdapter = new SearchVideoAdapter();
        searchVideoAdapter.setVideos(videos);
        searchVideoAdapter.setOnClickListener(this);
        binding.videoList.setLayoutManager(layoutManager);
        binding.videoList.setAdapter(searchVideoAdapter);

        binding.searchVideo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                videoViewModel.getVideos().observe(getViewLifecycleOwner(), new Observer<List<Videos>>() {
                    @Override
                    public void onChanged(List<Videos> list) {
                        filter.clear();
                        if (list != null) {
                            for (Videos video : list) {
                                if (video.getName().toLowerCase().contains(s.toLowerCase())) {
                                    filter.add(video);
                                }
                                searchVideoAdapter.filterList(filter);
                            }
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public void onSearchClick(int position, View view) {
        if (filter.isEmpty()){
            Bundle bundle = new Bundle();
            bundle.putString(Const.Sender.videoName, videos.get(position).getName());
            bundle.putString(Const.Sender.videoImageUrl, videos.get(position).getImage());
            bundle.putString(Const.Sender.videoUrl, videos.get(position).getVideo());
            VideoDetailActivity.starter(getContext(), bundle);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(Const.Sender.videoName, filter.get(position).getName());
            bundle.putString(Const.Sender.videoImageUrl, filter.get(position).getImage());
            bundle.putString(Const.Sender.videoUrl, filter.get(position).getVideo());
            VideoDetailActivity.starter(getContext(), bundle);
        }
    }
}
