package com.example.deluxe.View.Shopping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deluxe.Adapter.Shopping.OrderAdapter;
import com.example.deluxe.Entity.Address;
import com.example.deluxe.Entity.CartItem;
import com.example.deluxe.Interface.Model.AddressInterface;
import com.example.deluxe.Interface.PresenterView.Shopping.OrderInterface;
import com.example.deluxe.Model.AddressModel;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Presenter.Shopping.OrderPresenter;
import com.example.deluxe.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderInterface.OrderView {

    private List<CartItem> cartItemList;
    private RecyclerView recyclerView;
    private OrderInterface.OrderPresenter orderPresenter;
    private TextView name;
    private TextView phone;
    private TextView address;
    private TextView totalMoney;
    private Button buyProduct;
    private Address addressUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        this.init();

        orderPresenter.getAddress();

        this.totalMoney.setText(this.getTotalMoney()+"");

        this.buyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               confirmBuyProduct();
            }
        });
    }

    @Override
    public void loadView(Class view) {

    }

    @Override
    public void setNotification(Enum e) {

    }

    public void init()
    {
        this.orderPresenter = new OrderPresenter(this);
        Gson gson = new Gson();
        String json = getIntent().getStringExtra("list");
        this.cartItemList =  gson.fromJson(json, new TypeToken<List<CartItem>>(){}.getType());
        this.recyclerView = findViewById(R.id.orderlist);
        this.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.recyclerView.setLayoutManager(layoutManager);
        OrderAdapter orderAdapter = new OrderAdapter(this.cartItemList,this);
        recyclerView.setAdapter(orderAdapter);

        this.name = (TextView) findViewById(R.id.name);
        this.phone = (TextView) findViewById(R.id.phone);
        this.address = (TextView) findViewById(R.id.address);
        this.totalMoney = (TextView) findViewById(R.id.totalMoney);
        this.buyProduct = (Button) findViewById(R.id.buyProduct);
    }

    @Override
    public void setAddress(Address address) {
        this.addressUser = address;
        this.name.setText(address.getFullname());
        this.phone.setText(address.getPhone());
        this.address.setText(address.getAddress());
    }

    public Double getTotalMoney()
    {
        double total = 0;
        for (CartItem item : cartItemList)
        {
            total += item.getTotal();
        }

        return total;
    }

    public void confirmBuyProduct()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Bạn chấp nhận thanh toán trước chứ ?");
        dialog.setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               orderPresenter.buyProduct(cartItemList,addressUser,getTotalMoney());
            }
        });
        dialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}