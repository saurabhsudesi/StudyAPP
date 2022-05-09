package com.example.demo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    private static final String TAG = "DbHelper";
    public static String DB_NAME = "demo.sqlite";
    private static String DB_PATH = "";
    Context context;
    private  static  SQLiteDatabase mDb;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_PATH = "/data/data/" + context.getPackageName() + "databases";
        this.context = context;

    }

    private static final String TABLE_CREATE_LOGIN = "create table if not exists login(id integer primary key autoincrement, name text, email_id text, " +
            "contact_no text,address text ,password text)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.mDb = db;

        db.execSQL(TABLE_CREATE_LOGIN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
