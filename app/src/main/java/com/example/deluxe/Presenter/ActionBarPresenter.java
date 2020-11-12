package com.example.deluxe.Presenter;

import com.example.deluxe.Interface.PresenterView.ActionBarInterface;

public class ActionBarPresenter implements ActionBarInterface.ActionBarPresenter {
	ActionBarInterface.ActionBarView actionBarView;

	public ActionBarPresenter(ActionBarInterface.ActionBarView actionBarView) {
		this.actionBarView = actionBarView;
	}
}
