package com.example.hiddenbeyondofficial.control.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.hiddenbeyondofficial.model.Admins;
import com.example.hiddenbeyondofficial.model.News;
import com.example.hiddenbeyondofficial.model.Users;
import com.example.hiddenbeyondofficial.model.Videos;

public class DatabaseVideo extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "videos_db";
    private final static int VERSION = 1;

    private String TABLE_ADMIN = "admins";
    private String NAME_ADMIN = "name";
    private String PASSWORD_ADMIN = "password";

    private String TABLE_USER = "users";
    private String NAME_USER = "name";
    private String PHONE_USER = "phone";
    private String PASSWORD_USER = "password";
    private String ADDRESS_USER = "address";
    private String GENDER_USER = "gender";


    private String TABLE_VIDEO = "videos";
    private String NAME_VIDEO = "name";
    private String CATEGORY = "category";
    private String IMAGE_VIDEO = "image";
    private String VIDEO = "video";

    private String TABLE_NEWS = "news";
    private String NAME_NEWS = "name";
    private String IMAGE_NEWS = "image";
    private String CONTENT = "content";

    private String createTableAdmin = "CREATE TABLE " + TABLE_ADMIN + " ( " + NAME_ADMIN + " TEXT PRIMARY KEY UNIQUE, "
            + PASSWORD_ADMIN + " TEXT) ";

    private String createTableUser = "CREATE TABLE " + TABLE_USER + " ( " + NAME_USER + " TEXT PRIMARY KEY UNIQUE, "
            + PHONE_USER + " TEXT UNIQUE, "
            + PASSWORD_USER + " TEXT, "
            + ADDRESS_USER + " TEXT, "
            + GENDER_USER + " TEXT) ";

    private String createTableVideo = "CREATE TABLE " + TABLE_VIDEO + " ( " + NAME_VIDEO + " TEXT PRIMARY KEY UNIQUE, "
            + CATEGORY + " TEXT, "
            + IMAGE_VIDEO + " TEXT, "
            + VIDEO + " TEXT) ";

    private String createTableNews = "CREATE TABLE " + TABLE_NEWS + " ( " + NAME_NEWS + " TEXT PRIMARY KEY UNIQUE, "
            + IMAGE_NEWS + " TEXT, "
            + CONTENT + " TEXT) ";

    private String createAdmin = "INSERT INTO " + TABLE_ADMIN + " VAlUES ('admin','123')";
    private String createUser = "INSERT INTO " + TABLE_USER + " VAlUES ('1','1','1','1','Male')";
    private String createVideo = "INSERT INTO " + TABLE_VIDEO + " VAlUES ('Elisa Lam','Documentary','https://znews-photo.zingcdn.me/w660/Uploaded/aobovhp/2021_02_13/ezgif_5_58cc563f6240.jpg','3TjVBpyTeZM')";
    private String createNews = "INSERT INTO " + TABLE_NEWS + " VAlUES ('Elisa Lam','https://znews-photo.zingcdn.me/w660/Uploaded/ngogtn/2021_01_28/images_1_.jpeg','https://vi.wikipedia.org/wiki/Cái_chết_của_Elisa_Lam')";

    public DatabaseVideo(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableAdmin);
        sqLiteDatabase.execSQL(createTableUser);
        sqLiteDatabase.execSQL(createTableVideo);
        sqLiteDatabase.execSQL(createTableNews);

        sqLiteDatabase.execSQL(createAdmin);
        sqLiteDatabase.execSQL(createUser);
        sqLiteDatabase.execSQL(createVideo);
        sqLiteDatabase.execSQL(createNews);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getAdmin(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_ADMIN , null);
        return  res;
    }

    public Cursor getUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_USER , null);
        return  res;
    }

    public Cursor getVideo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_VIDEO , null);
        return  res;
    }

    public Cursor getNews(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NEWS , null);
        return  res;
    }

    public void addUser(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_USER, users.getName());
        values.put(PASSWORD_USER, users.getPassword());
        values.put(PHONE_USER, users.getPhone());
        values.put(ADDRESS_USER, users.getAddress());
        values.put(GENDER_USER, users.getGender());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addVideo(Videos videos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_VIDEO, videos.getName());
        values.put(CATEGORY, videos.getCategory());
        values.put(IMAGE_VIDEO, videos.getImage());
        values.put(VIDEO, videos.getVideo());

        db.insert(TABLE_VIDEO, null, values);
        db.close();
    }

    public void addNews(News news){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_NEWS, news.getName());
        values.put(IMAGE_NEWS, news.getImage());
        values.put(CONTENT, news.getContent());

        db.insert(TABLE_NEWS, null, values);
        db.close();
    }

    public void updateUser(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_USER, users.getName());
        values.put(PHONE_USER, users.getPhone());
        values.put(PASSWORD_USER, users.getPassword());
        values.put(ADDRESS_USER, users.getAddress());
        values.put(GENDER_USER, users.getGender());

        db.update(TABLE_USER, values, NAME_USER +" = " + users.getName(), null);
        db.close();
    }
    public void updateUserPassword(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD_USER, users.getPassword());

        db.update(TABLE_USER, values, NAME_USER +" = " + users.getName(), null);
        db.close();
    }


    public boolean checkUser(String username){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + NAME_USER + " = ?";
        String[] whereArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }

    public boolean checkVideo(String videoname){
        String query = "SELECT * FROM " + TABLE_VIDEO + " WHERE " + NAME_VIDEO + " = ?";
        String[] whereArgs = {videoname};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }

    public boolean checkNews(String newsName){
        String query = "SELECT * FROM " + TABLE_NEWS + " WHERE " + NAME_NEWS + " = ?";
        String[] whereArgs = {newsName};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }

    public boolean checkPhoneNumber(String phone){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + PHONE_USER + " = ?";
        String[] whereArgs = {phone};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }

    public boolean checkUserAndPhone(String phone, String username){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + PHONE_USER + " = ?" + " AND " + NAME_USER + " = ?";
        String[] whereArgs = {phone, username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }
}
