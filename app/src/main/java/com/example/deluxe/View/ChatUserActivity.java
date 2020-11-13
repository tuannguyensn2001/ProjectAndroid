package com.example.deluxe.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.deluxe.Adapter.ChatUserAdapter;
import com.example.deluxe.Entity.LastMessage;
import com.example.deluxe.Entity.Message;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.DepositInterface;
import com.example.deluxe.Interface.Model.MessageInterface;
import com.example.deluxe.Interface.PresenterView.ChatUserInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.MessageModel;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.Presenter.ChatUserPresenter;
import com.example.deluxe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatUserActivity extends AppCompatActivity implements ChatUserInterface.ChatUserView, ChatUserAdapter.OnUserListener {

    ChatUserInterface.ChatUserPresenter chatUserInterfacePresenter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_user);


        this.chatUserInterfacePresenter = new ChatUserPresenter(this);


         this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.recyclerView.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        this.recyclerView.setLayoutManager(layoutManager);


        this.chatUserInterfacePresenter.showData();









    }

    @Override
    public void onUserClick(int position) {



        chatUserInterfacePresenter.getItem(position);





    }

    @Override
    public void setAdapter(ArrayList<LastMessage> list) {
        ChatUserAdapter chatUserAdapter = new ChatUserAdapter(list,getApplicationContext(),this);
        recyclerView.setAdapter(chatUserAdapter);
    }

    @Override
    public void loadView(String email, Class view) {
        Intent intent = new Intent(this,view);
        intent.putExtra("emailReceiver",email);
        intent.putExtra("emailSender",Auth.getInstance().user().getEmail());
        startActivity(intent);
    }

    @Override
    public void loadView(Class view) {

    }

    @Override
    public void setNotification(Enum e) {

    }


}