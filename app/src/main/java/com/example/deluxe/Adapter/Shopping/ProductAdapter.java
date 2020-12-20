package com.example.deluxe.Adapter.Shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Entity.Product;
import com.example.deluxe.Helper.ConvertData;
import com.example.deluxe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
	List<Product> listProduct;
	Context context;
	OnProductListener onProductListener;

	public ProductAdapter(List<Product> listProduct, Context context, OnProductListener onProductListener) {
		this.onProductListener = onProductListener;
		this.listProduct = listProduct;
		this.context = context;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View itemView = layoutInflater.inflate(R.layout.component_product, parent, false);
		return new ViewHolder(itemView, onProductListener, listProduct);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.name.setText(this.listProduct.get(position).getName());
		holder.price.setText(ConvertData.moneyToString(this.listProduct.get(position).getPrice()));

		Picasso.get().load(this.listProduct.get(position).getThumbnail()).into(holder.image);
	}

	@Override
	public int getItemCount() {
		return this.listProduct.size();
	}

	public interface OnProductListener {
		void onClick(Product product);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView image;
		TextView name;
		TextView price;
		OnProductListener onProductListener;

		public ViewHolder(@NonNull final View itemView, final OnProductListener onProductListener, final List<Product> list) {
			super(itemView);
			this.onProductListener = onProductListener;
			this.image = itemView.findViewById(R.id.product_image);
			this.name = itemView.findViewById(R.id.product_name);
			this.price = itemView.findViewById(R.id.product_price);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onProductListener.onClick(list.get(getAdapterPosition()));
				}
			});
		}


	}
}
