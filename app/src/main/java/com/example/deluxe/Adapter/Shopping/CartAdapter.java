package com.example.deluxe.Adapter.Shopping;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Entity.CartItem;
import com.example.deluxe.Helper.ConvertData;
import com.example.deluxe.R;
import com.example.deluxe.View.Shopping.CartActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
	List<CartItem> cartItemList;
	Context context;
	OnAddNumberClick onChangeNumberClick;
	OnCheckBox onCheckBox;
	OnDeleteClick onDeleteClick;

	public CartAdapter(List<CartItem> list, Context context, OnAddNumberClick onChangeNumberClick, OnCheckBox onCheckBox, OnDeleteClick onDeleteClick) {
		this.cartItemList = list;
		this.context = context;
		this.onChangeNumberClick = onChangeNumberClick;
		this.onCheckBox = onCheckBox;
		this.onDeleteClick = onDeleteClick;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View itemView = layoutInflater.inflate(R.layout.component_product_changeable, parent, false);
		if (viewType != 0) {
			itemView.findViewById(R.id.product_remove_button).setVisibility(View.GONE);
			itemView.findViewById(R.id.product_minus_button).setVisibility(View.GONE);
			itemView.findViewById(R.id.product_add_button).setVisibility(View.GONE);
			itemView.findViewById(R.id.choose_button).setVisibility(View.GONE);
			return new ViewHolder(itemView, cartItemList);
		} else
			return new ViewHolder(itemView, this.onChangeNumberClick, cartItemList, onCheckBox, onDeleteClick);
	}

	@Override
	public int getItemViewType(int position) {
		return context instanceof CartActivity ? 0 : 1;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		CartItem cartItem = this.cartItemList.get(position);

		holder.number.setText(ConvertData.moneyToString(cartItem.getNumber()));
		holder.name.setText(cartItem.getProduct().getName());
		holder.description.setText(cartItem.getProduct().getDescription());
		holder.price.setText(ConvertData.moneyToString(cartItem.getProduct().getPrice()));
		holder.total.setText(ConvertData.moneyToString(cartItem.getTotal()));

		Picasso.get().load(cartItem.getProduct().getThumbnail()).into(holder.thumbnail);
	}

	@Override
	public int getItemCount() {
		return this.cartItemList.size();
	}

	public interface OnAddNumberClick {
		void onClick(CartItem cartItem, int position, int type);
	}

	public interface OnCheckBox {
		void onCheckTrue(CartItem cartItem);

		void onCheckFalse(CartItem cartItem);
	}

	public interface OnDeleteClick {
		void onDelete(CartItem cartItem, int position);
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		EditText number;
		ImageView thumbnail;
		TextView name;
		TextView description;
		TextView price;
		TextView total;
		OnAddNumberClick onChangeNumberClick;
		TextView plus, minus, remove;
		CheckBox checkBuyProduct;
		OnCheckBox onCheckBox;
		List<CartItem> list;

		public ViewHolder(@NonNull final View itemView,
						  final OnAddNumberClick onChangeNumberClick,
						  final List<CartItem> list,
						  final OnCheckBox onCheckBox,
						  final OnDeleteClick onDeleteClick) {
			super(itemView);
			this.list = list;
			this.onChangeNumberClick = onChangeNumberClick;
			this.onCheckBox = onCheckBox;
			init();
			initComplete();
		}

		public ViewHolder(@NonNull final View itemView,
						  final List<CartItem> list) {
			super(itemView);
			this.list = list;
			init();
		}

		private void init() {
			this.number = itemView.findViewById(R.id.product_quantity);
			this.thumbnail = itemView.findViewById(R.id.product_image);
			this.name = itemView.findViewById(R.id.product_name);
			this.description = itemView.findViewById(R.id.product_description);
			this.price = itemView.findViewById(R.id.product_price);
			this.total = itemView.findViewById(R.id.product_total_price);

			this.number.setEnabled(false);
		}

		private void initComplete() {
			this.plus = itemView.findViewById(R.id.product_add_button);
			this.minus = itemView.findViewById(R.id.product_minus_button);
			this.remove = itemView.findViewById(R.id.product_remove_button);
			this.checkBuyProduct = itemView.findViewById(R.id.choose_button);

			this.plus.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onChangeNumberClick.onClick(list.get(getAdapterPosition()), getAdapterPosition(), 1);
				}
			});
			this.minus.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onChangeNumberClick.onClick(list.get(getAdapterPosition()), getAdapterPosition(), 0);
				}
			});
			this.remove.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onDeleteClick.onDelete(list.get(getAdapterPosition()), getAdapterPosition());
				}
			});
			this.number.setEnabled(true);
			this.number.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {

				}

				@Override
				public void afterTextChanged(Editable s) {
//					TODO chinh so luong
				}
			});

			this.checkBuyProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						onCheckBox.onCheckTrue(list.get(getAdapterPosition()));
					} else {
						onCheckBox.onCheckFalse(list.get(getAdapterPosition()));
					}
				}
			});
		}
	}
}