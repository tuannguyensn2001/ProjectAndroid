package com.example.deluxe.Interface.PresenterView;

import android.net.Uri;

public interface AccountInterface {

    public interface AccountPresenter
    {
        void getAvatar();
        void receiveAvatar(Uri filePath);

    }

    public interface AccountView
    {
        void setAvatar(String url);
    }
}
