package com.example.deluxe.Presenter.Shopping;

import com.example.deluxe.Entity.CartItem;
import com.example.deluxe.Interface.Model.CartItemInterface;
import com.example.deluxe.Interface.PresenterView.Shopping.CartInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.CartModel;

import java.util.List;

public class CartPresenter implements CartInterface.CartPresenter {
	private CartInterface.CartView cartView;
	private List<CartItem> list;

	public CartPresenter(CartInterface.CartView cartView) {
		this.cartView = cartView;
	}

	@Override
	public void getCart() {

		new CartModel().getCart(Auth.getInstance().user().getUid(), new com.example.deluxe.Interface.Model.CartInterface() {
			@Override
			public void dataIsLoaded(List<CartItem> cartItemList) {
				list = cartItemList;
				cartView.setAdapter(cartItemList);
			}
		});
	}

	@Override
	public void increaseNumber(final CartItem cartItems, final int position) {
		new CartModel().increase(cartItems, new CartItemInterface() {
			@Override
			public void dataIsLoaded(CartItem cardItem) {
				list.set(position, cardItem);
				cartView.AdapterChanged(list, cardItem);
			}
		});
	}

	@Override
	public void decreaseNumber(final CartItem cartItems, final int position) {
		new CartModel().decrease(cartItems, new CartItemInterface() {
			@Override
			public void dataIsLoaded(CartItem cardItem) {
				if (cardItem.getId() == null) {
					list.remove(position);
				} else {
					list.set(position, cardItem);
				}

				cartView.AdapterChanged(list, cardItem);
			}
		});
	}

	@Override
	public void delete(final CartItem cartItem, final int position) {
		new CartModel().delete(cartItem, new CartItemInterface() {
			@Override
			public void dataIsLoaded(CartItem cardItem) {
				list.remove(position);

				cartView.AdapterChanged(list, cartItem);
			}

		});
	}
}
