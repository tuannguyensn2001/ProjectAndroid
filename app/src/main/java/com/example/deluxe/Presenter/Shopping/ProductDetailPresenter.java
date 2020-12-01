package com.example.deluxe.Presenter.Shopping;

import com.example.deluxe.Entity.Collection;
import com.example.deluxe.Entity.Product;
import com.example.deluxe.Interface.Model.ProductModelInterface;
import com.example.deluxe.Interface.PresenterView.Shopping.ProductDetailInterface;
import com.example.deluxe.Model.ProductModel;

import java.util.List;

public class ProductDetailPresenter implements ProductDetailInterface.ProductDetailPresenter {
	private ProductDetailInterface.ProductDetailView productDetailView;

	public ProductDetailPresenter(ProductDetailInterface.ProductDetailView productDetailView) {
		this.productDetailView = productDetailView;
	}


	@Override
	public void getProductDetail(int id) {
		new ProductModel().getProduct(id, new ProductModelInterface() {
			@Override
			public void dataIsLoaded(List<Collection> list) {

			}

			@Override
			public void dataIsLoaded(Product product) {
				productDetailView.loadData(product);
			}
		});
	}
}
