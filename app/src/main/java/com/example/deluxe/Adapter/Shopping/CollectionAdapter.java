package com.example.deluxe.Adapter.Shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Entity.Collection;
import com.example.deluxe.Entity.Product;
import com.example.deluxe.R;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {
	List<Collection> collectionList;
	Context context;
	ProductAdapter.OnProductListener onProductListener;

	public CollectionAdapter(List<Collection> list, Context context, ProductAdapter.OnProductListener onProductListener) {
		this.collectionList = list;
		this.context = context;
		this.onProductListener = onProductListener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View itemView = layoutInflater.inflate(R.layout.component_collection_item, parent, false);
		return new ViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.collectionName.setText(collectionList.get(position).getName());
		setProductAdapter(holder.recyclerView, this.collectionList.get(position).getProducts(), this.onProductListener);
	}

	@Override
	public int getItemCount() {
		return this.collectionList.size();
	}

	private void setProductAdapter(RecyclerView recyclerView, List<Product> list, ProductAdapter.OnProductListener onProductListener) {
		ProductAdapter productAdapter = new ProductAdapter(list, context, onProductListener);
		LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(productAdapter);
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView collectionName;
		RecyclerView recyclerView;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);

			this.collectionName = itemView.findViewById(R.id.category_name);
			this.recyclerView = itemView.findViewById(R.id.item_product);
		}
	}
}
