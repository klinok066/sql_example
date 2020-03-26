package com.example.sql_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql_example.R;
import com.example.sql_example.adapter.DataAdapter;
import com.example.sql_example.domain.User;
import com.example.sql_example.interactor.UsersInteractor;

import java.util.ArrayList;

public class SecondActivity  extends AppCompatActivity {

    UsersInteractor usersInteractor;
    String id;
    String userName;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<User> usersFirst= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        userName = intent.getExtras().getString("userName");
        id = intent.getExtras().getString("userId");
        setTitle(userName);
        users = usersInteractor.getUsers(5);
        setInitialData();
        init();

    }

    private void init() {
        RecyclerView recyclerView =  findViewById(R.id.recycleView);
        DataAdapter adapter = new DataAdapter(this, users);
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() {
        usersFirst = usersInteractor.getUsers();

        if (usersFirst != null) {
            for (User user : usersFirst) {
                users.add(user);
            }
        }
    }

}


