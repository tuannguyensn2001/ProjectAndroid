package com.example.deluxe.View.Shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Adapter.Shopping.CartAdapter;
import com.example.deluxe.Entity.CartItem;
import com.example.deluxe.Helper.ConvertData;
import com.example.deluxe.Interface.PresenterView.Shopping.CartInterface;
import com.example.deluxe.Presenter.Shopping.CartPresenter;
import com.example.deluxe.R;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartInterface.CartView, CartAdapter.OnAddNumberClick, CartAdapter.OnCheckBox, CartAdapter.OnDeleteClick {

	private CartInterface.CartPresenter cartPresenter;
	private RecyclerView recyclerView;
	private List<CartItem> pickedCartList;
	private TextView buyProduct;
	private TextView totalMoney;
	private List<CartItem> cartList;
	private CartAdapter cartAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);

		this.start();

	}

	public void start() {
		this.init();
		this.cartPresenter.getCart();

		this.buyProduct.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (pickedCartList.size() == 0) {
					Toast.makeText(CartActivity.this, "Bạn chưa chọn sản phẩm", Toast.LENGTH_LONG).show();
				} else {
					loadOrderView();

				}
			}
		});
	}


	public void init() {
		this.totalMoney = (TextView) findViewById(R.id.total_money);
		this.buyProduct = (TextView) findViewById(R.id.buy_button);
		this.pickedCartList = new LinkedList<>();
		this.cartPresenter = new CartPresenter(this);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.cart_action_bar_title));

		this.recyclerView = findViewById(R.id.cart_list);
		this.recyclerView.setHasFixedSize(true);
	}

	@Override
	public void setAdapter(List<CartItem> cartItemList) {
		this.cartList = cartItemList;
		this.cartAdapter = new CartAdapter(cartList, this, this, this, this);
		this.recyclerView.setAdapter(this.cartAdapter);
	}

	@Override
	public void AdapterChanged(List<CartItem> cartItemList, CartItem cartItem) {
		this.cartList = cartItemList;
		this.changeListPicked(cartItem);
		this.totalMoney.setText(ConvertData.moneyToString(this.getTotalMoney()));
		this.cartAdapter.notifyDataSetChanged();
	}

	public void changeListPicked(CartItem cartItem) {
		int index = -1;
		for (int i = 0; i < this.pickedCartList.size(); ++i) {
			if (this.pickedCartList.get(i).getId().equals(cartItem.getId())) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			this.pickedCartList.set(index, cartItem);
		}
	}


	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}


	@Override
	public void onClick(CartItem cartItem, int position, int type) {
		switch (type) {
			case 1:
				cartPresenter.increaseNumber(cartItem, position);
				break;

			case 0:
				cartPresenter.decreaseNumber(cartItem, position);
				break;
		}
	}

	@Override
	public void onCheckTrue(CartItem cartItem) {
		this.pickedCartList.add(cartItem);
		this.totalMoney.setText(ConvertData.moneyToString(this.getTotalMoney()));
	}

	@Override
	public void onCheckFalse(CartItem cartItem) {
		this.pickedCartList.remove(cartItem);
		this.totalMoney.setText(ConvertData.moneyToString(this.getTotalMoney()));
	}

	public Double getTotalMoney() {
		double total = 0;
		for (CartItem item : pickedCartList) {
			total += item.getTotal();
		}

		return total;
	}

	public void loadOrderView() {
		Intent intent = new Intent(this, OrderActivity.class);
		Gson gson = new Gson();
		String json = gson.toJson(this.pickedCartList);
		intent.putExtra("list", json);
		startActivity(intent);
	}

	@Override
	public void onDelete(CartItem cartItem, int position) {
		cartPresenter.delete(cartItem, position);

	}
}