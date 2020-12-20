package com.example.deluxe.View.Shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Adapter.Shopping.CollectionAdapter;
import com.example.deluxe.Adapter.Shopping.ProductAdapter;
import com.example.deluxe.Entity.Collection;
import com.example.deluxe.Entity.Product;
import com.example.deluxe.Interface.PresenterView.Shopping.ProductInterface;
import com.example.deluxe.Presenter.Shopping.ProductPresenter;
import com.example.deluxe.R;

import java.util.List;

public class ProductActivity extends AppCompatActivity implements ProductInterface.ProductView, ProductAdapter.OnProductListener {
	private ProductInterface.ProductPresenter productPresenter;
	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);

		this.init();

		productPresenter.getData();
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
		this.productPresenter = new ProductPresenter(this);
		this.recyclerView = findViewById(R.id.products);
		this.recyclerView.setHasFixedSize(true);
		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.product_action_bar_title));

		findViewById(R.id.cart_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(CartActivity.class);
			}
		});
	}

	@Override
	public void setAdapter(List<Collection> list) {
		CollectionAdapter collectionAdapter = new CollectionAdapter(list, this, this);
		this.recyclerView.setAdapter(collectionAdapter);
	}

	@Override
	public void loadView(Class view, Product product) {
		int id = product.getId();
		Intent intent = new Intent(this, view);
		intent.putExtra("id", id);
		startActivity(intent);
	}

	@Override
	public void onClick(Product product) {
		productPresenter.pickProduct(product);
	}
}