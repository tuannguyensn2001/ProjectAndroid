package com.example.deluxe.Core;

public interface View {
	void loadView(Class view);

	void setNotification(Enum e);

	interface ViewUseActionBar {
		void handleBackButton();
	}

	interface ViewUseCheckPasswordDialog {
		void handleIsUserCorrect(boolean b);
	}
}
