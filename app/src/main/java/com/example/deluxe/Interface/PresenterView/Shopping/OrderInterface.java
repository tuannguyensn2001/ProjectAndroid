package com.example.deluxe.Interface.PresenterView.Shopping;

import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.Address;
import com.example.deluxe.Entity.CartItem;

import java.util.List;

public interface OrderInterface {
	public interface OrderPresenter {
		void getAddress();

		void buyProduct(List<CartItem> list, Address address, double money);
	}

	public interface OrderView extends View {
		void setAddress(Address address);
	}
}
