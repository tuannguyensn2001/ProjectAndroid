package com.example.deluxe.Core;

public interface View {
	void loadView(Class<? extends View> view);

	void setNotification(Enum e);

	interface ViewUseActionBar {
	}

	interface ViewUseCheckPasswordDialog {
		void handleIsUserCorrect(boolean b);
	}
}
