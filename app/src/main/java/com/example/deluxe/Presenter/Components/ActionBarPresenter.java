package com.example.deluxe.Presenter.Components;

import com.example.deluxe.Interface.PresenterView.Components.ActionBarInterface;

public class ActionBarPresenter implements ActionBarInterface.ActionBarPresenter {
	ActionBarInterface.ActionBarView actionBarView;

	public ActionBarPresenter(ActionBarInterface.ActionBarView actionBarView) {
		this.actionBarView = actionBarView;
	}
}
