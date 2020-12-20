package com.example.deluxe.View.Shopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Adapter.Shopping.CartAdapter;
import com.example.deluxe.Entity.Address;
import com.example.deluxe.Entity.CartItem;
import com.example.deluxe.Helper.ConvertData;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.Shopping.OrderInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.WalletModel;
import com.example.deluxe.Presenter.Shopping.OrderPresenter;
import com.example.deluxe.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderInterface.OrderView {

	private List<CartItem> cartItemList;
	private RecyclerView recyclerView;
	private OrderInterface.OrderPresenter orderPresenter;
	private TextView name;
	private TextView phone;
	private TextView address;
	private TextView totalMoney;
	private TextView buyProduct;
	private Address addressUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);

		this.init();

		orderPresenter.getAddress();

		this.totalMoney.setText(ConvertData.moneyToString(this.getTotalMoney()));

		this.buyProduct.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				confirmBuyProduct();
			}
		});

	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}

	public void init() {
		this.orderPresenter = new OrderPresenter(this);
		Gson gson = new Gson();
		String json = getIntent().getStringExtra("list");
		this.cartItemList = gson.fromJson(json, new TypeToken<List<CartItem>>() {
		}.getType());

		this.recyclerView = findViewById(R.id.order_list);
		this.recyclerView.setHasFixedSize(true);
		CartAdapter orderAdapter = new CartAdapter(this.cartItemList, this, null, null, null);
		recyclerView.setAdapter(orderAdapter);

		this.name = findViewById(R.id.name);
		this.phone = findViewById(R.id.phone);
		this.address = findViewById(R.id.address);
		this.totalMoney = findViewById(R.id.total_money);
		this.buyProduct = findViewById(R.id.buy_button);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.order_action_bar_title));
	}

	@Override
	public void setAddress(Address address) {
		this.addressUser = address;
		this.name.setText(address.getFullname());
		this.phone.setText(address.getPhone());
		this.address.setText(address.getAddress());
	}

	public Double getTotalMoney() {
		double total = 0;
		for (CartItem item : cartItemList) {
			total += item.getTotal();
		}

		return total;
	}

	public void confirmBuyProduct() {
		final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Bạn chấp nhận thanh toán trước chứ ?");

		dialog.setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				orderPresenter.buyProduct(cartItemList, addressUser, getTotalMoney());
			}
		});
		dialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog alertDialog = dialog.create();

		new WalletModel().getMoneyOnce(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money_now) {
				if (getTotalMoney() > money_now) {
					dialog.setMessage("Số tiền này vượt úa hạn mức một lần của bạn");
				}
			}
		});

		alertDialog.show();
	}
}