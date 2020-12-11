package com.example.deluxe.Interface.PresenterView.Shopping;

import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.Collection;
import com.example.deluxe.Entity.Product;

import java.util.List;

public interface ProductInterface {

	public interface ProductPresenter {
		void getData();

		void pickProduct(Product product);
	}

	public interface ProductView extends View {
		void setAdapter(List<Collection> list);

		void loadView(Class view, Product product);

	}

}
