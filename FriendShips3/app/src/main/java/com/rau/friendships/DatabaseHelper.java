package com.rau.friendships;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;

    // generate create string
    private static final String TABLE_CREATE = "CREATE TABLE contacts (id integer primary key not null, " +
        "username text not null , email text not null , password text not null);";


    // constructor
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create database
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    public void insertUser(User user){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // generate id based on # of users in database
        String query = "SELECT * FROM users";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());

        // insert into the database
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public String searchUser(String username){
        db = this.getWritableDatabase();
        String query = "SELECT username, password FROM users";
        Cursor cursor = db.rawQuery(query, null);

        String u;
        String p = "not found";

        if (cursor.moveToFirst()){
            do {
                u = cursor.getString(0);
                if (u.equals(username)){
                    p = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }

        // return password
        return p;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS users";
        db.execSQL(query);
        this.onCreate(db);

    }
}
