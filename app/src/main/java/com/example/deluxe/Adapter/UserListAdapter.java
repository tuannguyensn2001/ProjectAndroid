package com.example.deluxe.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deluxe.Entity.User;
import com.example.deluxe.R;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter {
    ArrayList<User> userList;

    public UserListAdapter(ArrayList<User> userList) {
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return this.userList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.user_list, null);
        } else viewProduct = convertView;

        User user = (User) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.profile_username)).setText(String.format("%s", user.getUser()));
        ((TextView) viewProduct.findViewById(R.id.profile_email)).setText(String.format("%s", user.getEmail()));

        return viewProduct;
    }
}