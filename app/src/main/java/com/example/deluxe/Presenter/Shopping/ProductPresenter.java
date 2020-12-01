package com.example.deluxe.Presenter.Shopping;

import com.example.deluxe.Entity.Collection;
import com.example.deluxe.Entity.Product;
import com.example.deluxe.Interface.Model.ProductModelInterface;
import com.example.deluxe.Interface.PresenterView.Shopping.ProductInterface;
import com.example.deluxe.Model.ProductModel;
import com.example.deluxe.View.Shopping.ProductDetailActivity;

import java.util.List;

public class ProductPresenter implements ProductInterface.ProductPresenter {

	ProductInterface.ProductView productView;

	public ProductPresenter(ProductInterface.ProductView productView) {
		this.productView = productView;
	}

	@Override
	public void getData() {
		new ProductModel().getProducts(new ProductModelInterface() {
			@Override
			public void dataIsLoaded(List<Collection> list) {
				productView.setAdapter(list);
			}

			@Override
			public void dataIsLoaded(Product product) {

			}
		});
	}

	@Override
	public void pickProduct(Product product) {
		productView.loadView(ProductDetailActivity.class, product);
	}


}
