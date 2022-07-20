package com.example.hiddenbeyondofficial.view.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.FragmentHomeBinding;
import com.example.hiddenbeyondofficial.model.News;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.view.activity.InformationActivity;
import com.example.hiddenbeyondofficial.view.activity.LoginActivity;
import com.example.hiddenbeyondofficial.view.activity.StartActivity;
import com.example.hiddenbeyondofficial.view.activity.VideoDetailActivity;
import com.example.hiddenbeyondofficial.view.adapter.CategoryAdapter;
import com.example.hiddenbeyondofficial.view.adapter.MainRecyclerAdapter;
import com.example.hiddenbeyondofficial.control.rest.Callback;
import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.model.AllCategory;
import com.example.hiddenbeyondofficial.model.Videos;
import com.example.hiddenbeyondofficial.view.adapter.NewsAdapter;
import com.example.hiddenbeyondofficial.view.adapter.SearchVideoAdapter;
import com.example.hiddenbeyondofficial.viewmodel.NewsViewModel;
import com.example.hiddenbeyondofficial.viewmodel.VideoViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentHome extends Fragment {
    private MainRecyclerAdapter mainRecyclerAdapter;
    private NewsAdapter newsAdapter;
    private List<AllCategory> allCategories;
    private List<News> allNews;

    private FragmentHomeBinding binding;
    private VideoViewModel videoViewModel;
    private NewsViewModel newsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoViewModel = new VideoViewModel(getContext());
        newsViewModel = new NewsViewModel(getContext());

        List<Videos> homeCatListItem1 = new ArrayList<>();
        List<Videos> homeCatListItem2 = new ArrayList<>();
        List<Videos> homeCatListItem3 = new ArrayList<>();

        allNews = new ArrayList<>();

        allCategories = new ArrayList<>();
        allCategories.add(new AllCategory("Documentary", homeCatListItem1));
        allCategories.add(new AllCategory("Legend", homeCatListItem2));
        allCategories.add(new AllCategory("Movie", homeCatListItem3));

        binding.textTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMainRecycler(allCategories);
            }
        });

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

        newsViewModel.getNews().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> list) {
                for (News news : list){
                    allNews.add(news);
                }
                newsAdapter.notifyDataSetChanged();
            }
        });


        setMainRecycler(allCategories);
        setNewRecycler(allNews);

        binding.information.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                //InformationActivity.starter(getContext());
                showMenu();
            }
        });
    }

    public void setMainRecycler(List<AllCategory> allCategoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(allCategoryList);
        binding.mainRecycler.setAdapter(mainRecyclerAdapter);
    }

    public void setNewRecycler(List<News> news){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.newList.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter();
        newsAdapter.setNews(news);
        binding.newList.setAdapter(newsAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void showMenu(){
        PopupMenu popupMenu = new PopupMenu(getContext(), binding.information);
        popupMenu.setForceShowIcon(true);
        popupMenu.setGravity(Gravity.END);
        popupMenu.getMenuInflater().inflate(R.menu.item_main_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_info:
                        InformationActivity.starter(getContext());
                        break;
                    case R.id.menu_log_out:
                        GoogleSignInOptions gso = new GoogleSignInOptions.
                                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                build();

                        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(getContext(),gso);

                        StartActivity.starter(getContext());
                        googleSignInClient.signOut();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
