package com.example.demo.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demo.Model.UserModel;

import java.util.ArrayList;

public class DatabaseAdapter {
    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public DatabaseAdapter open() throws SQLException {
        try {

            database = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            dbHelper.onCreate(database);
        }

        return this;
    }

    public void close() {
        dbHelper.close();
    }


    //insert data in local DB
    public boolean insert(String values[], String names[], String tbl) {
        ContentValues initialValues = createContentValues(values, names);

        return database.insert(tbl, null, initialValues) > 0;
    }

    private ContentValues createContentValues(String values[], String names[]) {
        ContentValues values1 = new ContentValues();

        for (int i = 0; i < values.length; i++) {
            values1.put(names[i], values[i]);
            Log.e("values", "" + values.length);

        }

        return values1;
    }


    //get array list in local DB
    @SuppressLint("Range")
    public ArrayList<UserModel> getUSerList() {
        ArrayList<UserModel> complaints = new ArrayList<UserModel>();
        try {
            String sql = "Select * from login";
            Log.d("query", sql);
            database = dbHelper.getReadableDatabase();
            if (!database.isOpen()) {

                try {
                    database = dbHelper.getReadableDatabase();

                } catch (SQLException mSQLException) {
                    throw mSQLException;
                }
            }
            Cursor c = null;
            try {
                c = database.rawQuery(sql, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    if (c.moveToFirst() && c.getCount() > 0) {
                        do {
                            UserModel userModel = new UserModel();
                            userModel.setId(c.getString(c.getColumnIndex("id")));
                            userModel.setName(c.getString(c.getColumnIndex("name")));
                            userModel.setEmail_id(c.getString(c.getColumnIndex("email_id")));
                            userModel.setContact_no(c.getString(c.getColumnIndex("contact_no")));
                            userModel.setAddress(c.getString(c.getColumnIndex("address")));
                            userModel.setPassword(c.getString(c.getColumnIndex("password")));
                            Log.e("UserModel status++", userModel.getName());
                            complaints.add(userModel);

                        } while (c.moveToNext());
                        c.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        return complaints;
    }


    //delete locla data
    public boolean delete(String DATABASE_TABLE, String KEY_NAME, String value) {
        if (!database.isOpen()) {

            try {
                dbHelper.openDataBase();
                database = dbHelper.getReadableDatabase();

            } catch (SQLException mSQLException) {
                Log.e("TAG", "open >>" + mSQLException.toString());
                throw mSQLException;
            }
        }
        return database.delete(DATABASE_TABLE, KEY_NAME + "=" + value, null) > 0;
    }

    //update local data
    public int update(String table, ContentValues cv, String whereClause, String whereArgs[]) {

        if (!database.isOpen()) {

            try {
                dbHelper.openDataBase();
                database = dbHelper.getWritableDatabase();

            } catch (SQLException mSQLException) {
                Log.e("TAG", "open >>" + mSQLException.toString());
                throw mSQLException;
            }
        }
        int row_id = database.update(table, cv, whereClause, whereArgs);

        return row_id;
    }


}
