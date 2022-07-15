package com.example.hiddenbeyondofficial.control;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.hiddenbeyondofficial.control.rest.Callback;
import com.example.hiddenbeyondofficial.model.Admins;
import com.example.hiddenbeyondofficial.model.News;
import com.example.hiddenbeyondofficial.model.Users;
import com.example.hiddenbeyondofficial.model.Videos;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.util.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Repository {
    private FirebaseDatabase db;
    private DatabaseReference databaseReference;

    public Repository() {
        this.db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference();
    }

    public void getVideo(Callback callback) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Videos> list = new ArrayList<>();
                Videos videos;
                for (DataSnapshot data : snapshot.getChildren()) {
                    videos = data.getValue(Videos.class);
                    list.add(videos);
                }
                callback.getVideo(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child(Const.Database.video).addValueEventListener(postListener);
    }

    public void getAdmin(Callback callback) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Admins> list = new ArrayList<>();
                Admins admins;
                for (DataSnapshot data : snapshot.getChildren()) {
                    admins = data.getValue(Admins.class);
                    list.add(admins);
                }
                callback.getAdmin(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child(Const.Database.admin).addValueEventListener(postListener);
    }

    public void getUser(Callback callback) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Users> list = new ArrayList<>();
                Users users;
                for (DataSnapshot data : snapshot.getChildren()) {
                    users = data.getValue(Users.class);
                    list.add(users);
                }
                callback.getUser(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child(Const.Database.user).addValueEventListener(postListener);
    }

    public void getNews(Callback callback){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<News> list = new ArrayList<>();
                News news;
                for (DataSnapshot data : snapshot.getChildren()) {
                    news = data.getValue(News.class);
                    list.add(news);
                }
                callback.getNew(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child(Const.Database.news).addValueEventListener(postListener);
    }

    public void addUser(Users users, View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.child(Const.Database.user).child(users.getName()).exists()) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put(Const.Database.name, users.getName());
                    userdataMap.put(Const.Database.password, users.getPassword());
                    userdataMap.put(Const.Database.phone, users.getPhone());
                    userdataMap.put(Const.Database.address, users.getAddress());
                    userdataMap.put(Const.Database.gender, users.getGender());

                    databaseReference.child(Const.Database.user).child(users.getName()).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Utility.Notice.snack(view, Const.Success.created);
                            } else {
                                Utility.Notice.snack(view, Const.Error.network);
                            }
                        }
                    });
                } else {
                    Utility.Notice.snack(view, Const.Error.existed);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addVideo(Videos videos, View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.child(Const.Database.video).child(videos.getName()).exists()) {
                    HashMap<String, Object> videoDatamap = new HashMap<>();
                    videoDatamap.put(Const.Database.name, videos.getName());
                    videoDatamap.put(Const.Database.image, videos.getImage());
                    videoDatamap.put(Const.Database.videos, videos.getVideo());
                    videoDatamap.put(Const.Database.category, videos.getCategory());

                    databaseReference.child(Const.Database.video).child(videos.getName()).updateChildren(videoDatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Utility.Notice.snack(view, Const.Success.created);
                            } else {
                                Utility.Notice.snack(view, Const.Error.network);
                            }
                        }
                    });
                } else {
                    Utility.Notice.snack(view, Const.Error.existed);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addNews(News news, View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.child(Const.Database.news).child(news.getName()).exists()) {
                    HashMap<String, Object> videoDatamap = new HashMap<>();
                    videoDatamap.put(Const.Database.name, news.getName());
                    videoDatamap.put(Const.Database.image, news.getImage());
                    videoDatamap.put(Const.Database.content, news.getContent());

                    databaseReference.child(Const.Database.news)
                            .child(news.getName())
                            .updateChildren(videoDatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Utility.Notice.snack(view, Const.Success.created);
                            } else {
                                Utility.Notice.snack(view, Const.Error.network);
                            }
                        }
                    });
                } else {
                    Utility.Notice.snack(view, Const.Error.existed);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateUser(Users users) {
        databaseReference.child(Const.Database.user).child(users.getName()).setValue(users);
    }

    public void updatePassword(Users users, View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(Const.Database.user).child(users.getName()).exists()) {
                    Utility.Notice.snack(view, Const.Success.update);
                    databaseReference.child(Const.Database.user).child(users.getName()).child(Const.Database.password).setValue(users.getPassword());
                } else {
                    Utility.Notice.snack(view, Const.Error.notexisted);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
