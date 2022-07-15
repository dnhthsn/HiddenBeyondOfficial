package com.example.hiddenbeyondofficial.control.rest;

import com.example.hiddenbeyondofficial.model.Admins;
import com.example.hiddenbeyondofficial.model.News;
import com.example.hiddenbeyondofficial.model.Users;
import com.example.hiddenbeyondofficial.model.Videos;

import java.util.List;

public abstract class Callback {
    public void getUser(List<Users> list){}
    public void getAdmin(List<Admins> list){}
    public void getVideo(List<Videos> list){}
    public void getNew(List<News> list){}
}
