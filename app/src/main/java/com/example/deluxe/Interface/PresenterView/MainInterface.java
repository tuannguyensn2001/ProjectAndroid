package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.View;

public interface MainInterface {
    public interface MainPresenter
    {
        void handleLogOut();
        void handleNapThe();

    }

    public interface MainView extends View
    {

    }
}
