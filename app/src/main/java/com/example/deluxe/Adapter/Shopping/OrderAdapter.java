package com.example.deluxe.Adapter.Shopping;

import android.content.Context;
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
import com.example.deluxe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>
{
    List<CartItem> cartItemList;
    Context context;


    public OrderAdapter(List<CartItem> list, Context context)
    {
        this.cartItemList = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.component_order_item,parent,false);
        return new ViewHolder(itemView);
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



        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.number = itemView.findViewById(R.id.product_quantity);
            this.thumbnail = itemView.findViewById(R.id.product_image);
            this.name = itemView.findViewById(R.id.product_name);
            this.description = itemView.findViewById(R.id.product_description);
            this.price = itemView.findViewById(R.id.product_price);
            this.total = itemView.findViewById(R.id.product_total_price);




        }
    }




}
