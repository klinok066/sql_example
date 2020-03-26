package com.example.sql_example.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sql_example.domain.User;

import java.util.ArrayList;

public class DatabaseRepository {
    private final String TAG = "DatabaseRepository";
    DatabaseHelper databaseHelper;
    DatabaseHelper databaseHelper2;

    public DatabaseRepository(Context context) {
        initDb(context);
    }

    private void initDb(Context context) {
        databaseHelper = new DatabaseHelper(context, "User", null, 1);
        databaseHelper2 = new DatabaseHelper(context, "Friendship", null, 1);
    }

    public boolean insertUser(String name, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        if (getUser(name, password) != null) {
            return false;
        } else {
            db.execSQL(SQLScripts.insertUserScript(name, password));
            return true;
        }
    }

    public User getUser(String name, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.getUserScript(name, password), null);
        // Cтавим позицию курсора на первую строку выборки
        // Eсли в выборке нет строк, вернется false
        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = userCursor.getColumnIndex("id");
            int nameColIndex = userCursor.getColumnIndex("name");
            int passwordColIndex = userCursor.getColumnIndex("password");

            do {
                // получаем значения по номерам столбцов
                User user = new User(userCursor.getString(idColIndex),
                        userCursor.getString(nameColIndex),
                        userCursor.getString(passwordColIndex));
                userCursor.close();
                return user;
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (userCursor.moveToNext());
        } else {
            userCursor.close();
            return null;
        }
    }

    public ArrayList<User> getUsers(int limit) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.getAllUsersScript(limit), null);
        // Cтавим позицию курсора на первую строку выборки
        // Eсли в выборке нет строк, вернется false
        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = userCursor.getColumnIndex("id");
            int nameColIndex = userCursor.getColumnIndex("name");
            int passwordColIndex = userCursor.getColumnIndex("password");

            ArrayList<User> userList = new ArrayList();
            do {
                // получаем значения по номерам столбцов
                User user = new User(userCursor.getString(idColIndex),
                        userCursor.getString(nameColIndex),
                        userCursor.getString(passwordColIndex));
                userList.add(user);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (userCursor.moveToNext());
            userCursor.close();
            return userList;
        } else {
            userCursor.close();
            return null;
        }
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.getAllUsersScript(), null);
        // Cтавим позицию курсора на первую строку выборки
        // Eсли в выборке нет строк, вернется false
        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = userCursor.getColumnIndex("id");
            int nameColIndex = userCursor.getColumnIndex("name");
            int passwordColIndex = userCursor.getColumnIndex("password");

            ArrayList<User> userList = new ArrayList();
            do {
                // получаем значения по номерам столбцов
                User user = new User(userCursor.getString(idColIndex),
                        userCursor.getString(nameColIndex),
                        userCursor.getString(passwordColIndex));
                userList.add(user);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (userCursor.moveToNext());
            userCursor.close();
            return userList;
        } else {
            userCursor.close();
            return null;
        }
    }

    public boolean insertFriendship(int myUserId, int friendUserId){
        SQLiteDatabase db = databaseHelper2.getWritableDatabase();
        if(checkOutgoingFriendship(myUserId, friendUserId) != -1){
            return false;
        } else {
            db.execSQL(SQLScripts.insertFriendshipScript(myUserId, friendUserId));
            return true;
        }
    }

    public int checkIncomingFriendship(int myUserId, int friendUserId) {
        SQLiteDatabase db = databaseHelper2.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.checkIncomingFriendshipScript(myUserId, friendUserId), null);

        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int isconfirmColIndex = userCursor.getColumnIndex("isconfirm");

            int check = userCursor.getInt(isconfirmColIndex);
            userCursor.close();
            return check;
        } else {
            userCursor.close();
            return -1;
        }
    }


    public int checkOutgoingFriendship(int myUserId, int friendUserId) {
        SQLiteDatabase db = databaseHelper2.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.checkOutgoingFriendshipScript(myUserId, friendUserId), null);

        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int isconfirmColIndex = userCursor.getColumnIndex("isconfirm");

            int check = userCursor.getInt(isconfirmColIndex);
            userCursor.close();
            return check;
        } else {
            userCursor.close();
            return -1;
        }
    }

    public boolean confirmFriendship(int myUserId, int friendUserId, int status) {
        SQLiteDatabase db = databaseHelper2.getWritableDatabase();
        if(checkOutgoingFriendship(myUserId, friendUserId) >= 0){
            if (status == 1) {
                db.execSQL(SQLScripts.updateFriendshipScript(myUserId, friendUserId));
            }else {
                db.execSQL(SQLScripts.deleteFriendshipScript(myUserId, friendUserId));
            }
            return true;
        }
        return false;
    }

}
