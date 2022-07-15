package com.example.hiddenbeyondofficial.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.FragmentHomeBinding;
import com.example.hiddenbeyondofficial.databinding.FragmentVideoBinding;
import com.example.hiddenbeyondofficial.model.AllCategory;
import com.example.hiddenbeyondofficial.model.Videos;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.view.activity.VideoDetailActivity;
import com.example.hiddenbeyondofficial.view.adapter.CategoryAdapter;
import com.example.hiddenbeyondofficial.view.adapter.MainRecyclerAdapter;
import com.example.hiddenbeyondofficial.view.adapter.SearchVideoAdapter;
import com.example.hiddenbeyondofficial.viewmodel.VideoViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentVideo extends Fragment implements CategoryAdapter.clickListener, SearchVideoAdapter.onClickListener {
    private MainRecyclerAdapter mainRecyclerAdapter;
    private CategoryAdapter categoryAdapter;
    private SearchVideoAdapter searchVideoAdapter;
    private List<AllCategory> allCategories;
    private List<String> categories;
    private List<Videos> videos;

    private FragmentVideoBinding binding;
    private VideoViewModel videoViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoViewModel = new VideoViewModel();

        List<Videos> homeCatListItem1 = new ArrayList<>();
        List<Videos> homeCatListItem2 = new ArrayList<>();
        List<Videos> homeCatListItem3 = new ArrayList<>();

        videos = new ArrayList<>();
        categories = new ArrayList<>();
        allCategories = new ArrayList<>();
        allCategories.add(new AllCategory("Documentary", homeCatListItem1));
        allCategories.add(new AllCategory("Legend", homeCatListItem2));
        allCategories.add(new AllCategory("Movie", homeCatListItem3));

        videoViewModel.getVideos().observe(getViewLifecycleOwner(), new Observer<List<Videos>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<Videos> videos) {
                homeCatListItem1.clear();
                homeCatListItem2.clear();
                homeCatListItem3.clear();
                for (Videos video : videos) {
                    switch (video.getCategory()) {
                        case "Documentary":
                            homeCatListItem1.add(video);
                            break;
                        case "Legend":
                            homeCatListItem2.add(video);
                            break;
                        case "Movie":
                            homeCatListItem3.add(video);
                            break;
                    }
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }
        });
        binding.allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMainRecycler(allCategories);
            }
        });


        String[] strings = getResources().getStringArray(R.array.category);
        categories.addAll(Arrays.asList(strings));

        setMainRecycler(allCategories);
        setCategoryAdapter(categories);
    }

    public void setMainRecycler(List<AllCategory> allCategoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.videoList.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(allCategoryList);
        binding.videoList.setAdapter(mainRecyclerAdapter);
    }

    public void setCategoryAdapter(List<String> categories) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.categoryList.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(categories);
        categoryAdapter.setClickListener(this);
        binding.categoryList.setAdapter(categoryAdapter);
    }

    public void setMainRecyclerItem(List<Videos> videos) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.videoList.setLayoutManager(layoutManager);
        searchVideoAdapter = new SearchVideoAdapter();
        searchVideoAdapter.setVideos(videos);
        searchVideoAdapter.setOnClickListener(this);
        binding.videoList.setAdapter(searchVideoAdapter);
    }

    @Override
    public void onClick(int position, View view, int selectedItem) {
        String category = categories.get(position);
        videoViewModel.getVideos().observe(getViewLifecycleOwner(), new Observer<List<Videos>>() {
            @Override
            public void onChanged(List<Videos> list) {
                videos.clear();
                for (Videos video : list) {
                    if (video.getCategory().equals(category)) {
                        videos.add(video);
                    }
                }
            }
        });
        setMainRecyclerItem(videos);
    }

    @Override
    public void onSearchClick(int position, View view) {
        Bundle bundle = new Bundle();
        bundle.putString(Const.Sender.videoName, videos.get(position).getName());
        bundle.putString(Const.Sender.videoImageUrl, videos.get(position).getImage());
        bundle.putString(Const.Sender.videoUrl, videos.get(position).getVideo());
        VideoDetailActivity.starter(getContext(), bundle);
    }
}
