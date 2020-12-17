package com.example.deluxe.View.Shopping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deluxe.Entity.Product;
import com.example.deluxe.Helper.ConvertData;
import com.example.deluxe.Interface.PresenterView.Shopping.ProductDetailInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.CartModel;
import com.example.deluxe.Presenter.Shopping.ProductDetailPresenter;
import com.example.deluxe.R;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailInterface.ProductDetailView {
	private int id;
	private ProductDetailInterface.ProductDetailPresenter productDetailPresenter;
	private ImageView thumbnail;
	private TextView name;
	private TextView price;
	private TextView description;
	private Button addToCart;
	private Button cart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);

		this.id = getIntent().getIntExtra("id", 0);
		this.init();
		this.productDetailPresenter.getProductDetail(this.id);

		this.addToCart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new CartModel().addToCart(Auth.getInstance().user().getUid(), id);
			}
		});

		this.cart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(CartActivity.class);
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
		this.productDetailPresenter = new ProductDetailPresenter(this);

		this.thumbnail = findViewById(R.id.thumbnail);
		this.name = findViewById(R.id.name);
		this.price = findViewById(R.id.price);
		this.description = findViewById(R.id.description);
		this.addToCart = findViewById(R.id.addtocart);
		this.cart = findViewById(R.id.cart);
	}

	@SuppressLint("SetTextI18n")
	@Override
	public void loadData(Product product) {
		this.name.setText(product.getName());
		this.price.setText(ConvertData.moneyToString(product.getPrice()));
		this.description.setText(product.getDescription());

		Picasso.get().load(product.getThumbnail()).into(this.thumbnail);

	}
}