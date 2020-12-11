package com.example.deluxe.Interface.Model;

import com.example.deluxe.Entity.Collection;
import com.example.deluxe.Entity.Product;

import java.util.List;

public interface ProductModelInterface {

	void dataIsLoaded(List<Collection> list);

	void dataIsLoaded(Product product);
}
