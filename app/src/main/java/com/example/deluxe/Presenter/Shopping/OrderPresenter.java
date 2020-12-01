package com.example.deluxe.Presenter.Shopping;

import com.example.deluxe.Entity.Address;
import com.example.deluxe.Entity.CartItem;
import com.example.deluxe.Interface.Model.AddressInterface;
import com.example.deluxe.Interface.PresenterView.Shopping.OrderInterface;
import com.example.deluxe.Model.AddressModel;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.OrderModel;

import java.util.List;

public class OrderPresenter implements OrderInterface.OrderPresenter {
	private OrderInterface.OrderView orderView;

	public OrderPresenter(OrderInterface.OrderView orderView) {
		this.orderView = orderView;
	}

	@Override
	public void getAddress() {
		new AddressModel().getAddress(Auth.getInstance().user().getUid(), new AddressInterface() {
			@Override
			public void dataIsLoaded(Address address) {
				orderView.setAddress(address);
			}
		});
	}

	@Override
	public void buyProduct(List<CartItem> list, Address address, double money) {
		new OrderModel().create(list, address, money);
	}
}
