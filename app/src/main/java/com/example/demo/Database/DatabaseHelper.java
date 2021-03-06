package com.example.demo.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final int DB_VERSION = 1;
    private static final String TAG = "DbHelper";

    public static String DB_NAME = "demo.sqlite";
    private static String DB_PATH = "";
    private static SQLiteDatabase mDb;
    Context context;


//create table
    private static final String TABLE_CREATE_LOGIN = "create table if not exists login(id integer primary key autoincrement, name text, email_id text, " +
            "contact_no text,address text ,password text)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_PATH = "/data/data/" + context.getPackageName() + "databases";
        this.context = context;

    }

//    open database
    public boolean openDataBase() throws SQLException {
        // Open the database
        String myPath = DB_PATH + DB_NAME;
        mDb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDb != null;
    }

//on create method
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.mDb = db;

        db.execSQL(TABLE_CREATE_LOGIN);

    }


    /*on Upgrade Method*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
