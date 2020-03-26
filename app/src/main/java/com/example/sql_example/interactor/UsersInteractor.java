package com.example.sql_example.interactor;

import android.content.Context;

import com.example.sql_example.domain.User;
import com.example.sql_example.repository.DatabaseRepository;

import java.util.ArrayList;

public class UsersInteractor {
    DatabaseRepository repository;

    public UsersInteractor(Context context) {
        repository = new DatabaseRepository(context);
    }

    public boolean insertUser(String name, String password) {
        return repository.insertUser(name, password);
    }

    public User getUser(String name, String password) {
        return repository.getUser(name, password);
    }

    public ArrayList<User> getUsers(int limit) {
        return repository.getUsers(limit);
    }

    public ArrayList<User> getUsers() {
        return repository.getUsers();
    }

    public boolean insertFriendship(int myUserId, int friendUserId){
        return repository.insertFriendship(myUserId, friendUserId);
    }

    public int checkIncomingFriendship(int myUserId, int friendUserId){
        return repository.checkIncomingFriendship(myUserId, friendUserId);
    }

    public int checkOutgoingFriendship(int myUserId, int friendUserId){
        return repository.checkOutgoingFriendship(myUserId, friendUserId);
    }

    public boolean confirmFriendship(int myUserId, int friendUserId, int status){
        return repository.confirmFriendship(myUserId, friendUserId, status);
    }
}
