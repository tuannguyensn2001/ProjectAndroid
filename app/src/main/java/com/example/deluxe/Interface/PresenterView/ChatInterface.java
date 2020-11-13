package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Entity.Message;

import java.util.ArrayList;

public interface ChatInterface {

    public interface ChatView
    {

        void setAdapter(ArrayList<Message> list);
    }

    public interface ChatPresenter
    {
        void handleInputMessage(Message message);
        void getListMessage(Message message);
    }

}
