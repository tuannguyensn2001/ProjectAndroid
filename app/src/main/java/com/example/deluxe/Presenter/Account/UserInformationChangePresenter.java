package com.example.deluxe.Presenter.Account;

import com.example.deluxe.Entity.Address;
import com.example.deluxe.Interface.Model.AddressInterface;
import com.example.deluxe.Interface.PresenterView.Account.UserInformationChangeInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;

public class UserInformationChangePresenter implements UserInformationChangeInterface.UserInformationChangePresenter {

	UserInformationChangeInterface.UserInformationChangeView userInformationChangeView;

	public UserInformationChangePresenter(final UserInformationChangeInterface.UserInformationChangeView userInformationChangeView) {
		this.userInformationChangeView = userInformationChangeView;

		getAddress(new AddressInterface() {
			@Override
			public void dataIsLoaded(Address address) {
				userInformationChangeView.setData(address);
			}
		});
	}

	@Override
	public void handleInformationChange(String username, String phone, String address) {
		new UserModel().editAddress(Auth.getInstance().user().getUid(), username, phone, address);
	}

	public void getAddress(AddressInterface addressInterface) {
		new UserModel().showAddress(Auth.getInstance().user().getUid(), addressInterface);
	}

}