package com.example.sql_example.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final String TAG = "DatabaseHelper";
    String name;
    String script;

    public DatabaseHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        this.name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        script = "initDb" + name + "Script()";
        Log.d(TAG, "Инициализация базы...");
        if(script.equals("initDbUserScript()")) {
            db.execSQL(SQLScripts.initDbUserScript());
            Log.d(TAG, "База 1 инициализирована");
        }else {
            db.execSQL(SQLScripts.initDbFriendshipScript());
            Log.d(TAG, "База 2 инициализирована");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
