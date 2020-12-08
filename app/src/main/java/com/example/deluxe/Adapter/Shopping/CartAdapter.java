package com.example.deluxe.Adapter.Shopping;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Entity.CartItem;
import com.example.deluxe.Entity.Product;
import com.example.deluxe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
{
    List<CartItem> cartItemList;
    Context context;
    OnAddNumberClick onChangeNumberClick;
    OnCheckBox onCheckBox;

    public CartAdapter(List<CartItem> list, Context context, OnAddNumberClick onChangeNumberClick, OnCheckBox onCheckBox)
    {
        this.cartItemList = list;
        this.context = context;
        this.onChangeNumberClick = onChangeNumberClick;
        this.onCheckBox = onCheckBox;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.component_product_changeable,parent,false);
        return new ViewHolder(itemView, this.onChangeNumberClick,cartItemList,onCheckBox);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = this.cartItemList.get(position);
        holder.number.setText(cartItem.getNumber()+"");
        holder.name.setText(cartItem.getProduct().getName());
        holder.description.setText(cartItem.getProduct().getDescription());
        holder.price.setText(cartItem.getProduct().getPrice()+"");
        holder.total.setText(cartItem.getTotal()+"");
        Picasso.get().load(cartItem.getProduct().getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return this.cartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        EditText number;
        ImageView thumbnail;
        TextView name;
        TextView description;
        TextView price;
        TextView total;
        OnAddNumberClick onChangeNumberClick;
        Button plus;
        Button minus;
        CheckBox checkBuyProduct;
        OnCheckBox onCheckBox;

        public ViewHolder(@NonNull final View itemView,
                          final OnAddNumberClick onChangeNumberClick,
                          final List<CartItem> list,
                          final OnCheckBox onCheckBox) {
            super(itemView);

            this.number = itemView.findViewById(R.id.product_quantity);
            this.thumbnail = itemView.findViewById(R.id.product_image);
            this.name = itemView.findViewById(R.id.product_name);
            this.description = itemView.findViewById(R.id.product_description);
            this.price = itemView.findViewById(R.id.product_price);
            this.total = itemView.findViewById(R.id.product_total_price);
            this.plus = itemView.findViewById(R.id.product_add_button);
            this.minus = itemView.findViewById(R.id.product_minus_button);
            this.checkBuyProduct = itemView.findViewById(R.id.checkBuyProduct);
            this.onChangeNumberClick = onChangeNumberClick;

            this.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChangeNumberClick.onClick(list.get(getAdapterPosition()),getAdapterPosition(),1);
                }
            });
            this.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChangeNumberClick.onClick(list.get(getAdapterPosition()),getAdapterPosition(),0);
                }
            });

            this.checkBuyProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                    {
                        onCheckBox.onCheckTrue(list.get(getAdapterPosition()));
                    } else{
                        onCheckBox.onCheckFalse(list.get(getAdapterPosition()));
                    }
                }
            });


        }
    }

    public interface OnAddNumberClick
    {
        void onClick(CartItem cartItem,int position,int type);
    }

    public interface OnCheckBox
    {
        void onCheckTrue(CartItem cartItem);
        void onCheckFalse(CartItem cartItem);
    }
}
