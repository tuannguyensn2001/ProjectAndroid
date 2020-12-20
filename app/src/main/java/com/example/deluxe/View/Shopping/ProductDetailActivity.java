package com.example.deluxe.View.Shopping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.deluxe.Entity.Attribute;
import com.example.deluxe.Entity.Product;
import com.example.deluxe.Helper.ConvertData;
import com.example.deluxe.Interface.PresenterView.Shopping.ProductDetailInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.CartModel;
import com.example.deluxe.Presenter.Shopping.ProductDetailPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailInterface.ProductDetailView {
	private int id;
	private ProductDetailInterface.ProductDetailPresenter productDetailPresenter;
	private ImageView thumbnail;
	private TextView name;
	private TextView price;
	private TextView description;
	private TextView addToCart;
	private CollapsingToolbarLayout collapsingToolbarLayout;
	private LinearLayout listDescription;
	private List<Attribute> attributes;
	private ImageView backButton, homeButton, cartButton;
	private NestedScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);

		this.id = getIntent().getIntExtra("id", 0);
		this.init();

		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		homeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(MainActivity.class);
			}
		});
		cartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(CartActivity.class);
			}
		});
		this.productDetailPresenter.getProductDetail(this.id);

		this.addToCart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new CartModel().addToCart(Auth.getInstance().user().getUid(), id);
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

		this.backButton = findViewById(R.id.pd_back_button);
		this.cartButton = findViewById(R.id.pd_cart_button);
		this.homeButton = findViewById(R.id.pd_home_button);

		this.thumbnail = findViewById(R.id.thumbnail);
		this.collapsingToolbarLayout = findViewById(R.id.collapse_toolbar);

		this.listDescription = findViewById(R.id.list_description);

		this.price = findViewById(R.id.product_detail_price);
		this.description = findViewById(R.id.product_detail_description);
		this.scrollView = findViewById(R.id.scroll_view);
		this.name = findViewById(R.id.product_detail_name);
		this.addToCart = findViewById(R.id.add_to_cart_button);
	}

	@SuppressLint("SetTextI18n")
	@Override
	public void loadData(Product product) {
		this.name.setText(product.getName());
		collapsingToolbarLayout.setTitle(product.getName());
		this.price.setText(ConvertData.moneyToString(product.getPrice()) + " $");
		this.description.setText(product.getDescription());
		Picasso.get().load(product.getThumbnail()).into(this.thumbnail);
		attributes = product.getAttributes();
		bindAttributes();
	}

	private void bindAttributes() {
		if (attributes.isEmpty()) return;
		for (Attribute attribute : attributes) {
			bindView(attribute);
		}
	}

	private void bindView(Attribute attribute) {
		View convertView = LayoutInflater.from(this).inflate(R.layout.component_product_detail, scrollView, false);
		((TextView) convertView.findViewById(R.id.info_description_title)).setText(attribute.getName());
		((TextView) convertView.findViewById(R.id.info_description)).setText(attribute.getValue());

		listDescription.addView(convertView);
	}
}