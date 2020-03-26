package com.example.sql_example.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.sql_example.R;
import com.example.sql_example.domain.User;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<User> users;

    public DataAdapter(Context context, List<User> users) {
        this.users = users;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.imageView.setImageResource(R.raw.s1200);
        holder.button.setText("Добавить");
        holder.userName.setText(user.name);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView userName, isFriend;
        final Button button;

        ViewHolder(View view){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.photo);
            button = (Button) view.findViewById(R.id.button);
            userName = (TextView) view.findViewById(R.id.userName);
            isFriend = (TextView) view.findViewById(R.id.isFriend);
        }
    }
}