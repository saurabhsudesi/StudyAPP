package com.example.demo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseCon {
    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DatabaseCon(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public DatabaseCon open() throws SQLException {
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

    public boolean insert(String values[], String names[], String tbl) {
        ContentValues initialValues = createContentValues(values, names);

        return database.insert(tbl, null, initialValues)>0;
    }

    private ContentValues createContentValues(String values[], String names[]) {
        ContentValues values1 = new ContentValues();

        for (int i = 0; i < values.length; i++) {
            values1.put(names[i], values[i]);
            Log.e("values",""+values.length);

        }

        return values1;
    }
}
