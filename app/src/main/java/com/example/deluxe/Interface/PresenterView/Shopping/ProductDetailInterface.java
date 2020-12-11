package com.example.deluxe.Interface.PresenterView.Shopping;

import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.Product;

public interface ProductDetailInterface {

	public interface ProductDetailPresenter {
		void getProductDetail(int id);
	}

	public interface ProductDetailView extends View {
		void loadData(Product product);
	}
}
