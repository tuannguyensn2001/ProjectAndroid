package com.example.deluxe.Interface.PresenterView.Shopping;

import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.CartItem;

import java.util.List;

public interface CartInterface {

	interface CartPresenter {
		void getCart();

		void increaseNumber(CartItem cartItem, int position);

		void decreaseNumber(CartItem cartItem, int position);

		void delete(CartItem cartItem, int position);
	}

	interface CartView extends View {
		void setAdapter(List<CartItem> cartItemList);

		void AdapterChanged(List<CartItem> cartItemList, CartItem cartItem);
	}
}
