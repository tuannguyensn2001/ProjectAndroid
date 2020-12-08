package com.example.deluxe.View.Shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deluxe.Entity.Product;
import com.example.deluxe.Interface.PresenterView.Shopping.ProductDetailInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.CardModel;
import com.example.deluxe.Model.CartModel;
import com.example.deluxe.Presenter.Shopping.ProductDetailPresenter;
import com.example.deluxe.R;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailInterface.ProductDetailView
{
    private int id;
    private ProductDetailInterface.ProductDetailPresenter productDetailPresenter;
    private ImageView thumbnail;
    private TextView name;
    private TextView price;
    private TextView description;
    private Button addToCart;
    private Button cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        this.id = getIntent().getIntExtra("id",0);
        this.init();
        this.productDetailPresenter.getProductDetail(this.id);

        this.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CartModel().addToCart(Auth.getInstance().user().getUid(),id);
            }
        });

        this.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadView(CartActivity.class);
            }
        });

    }

    @Override
    public void loadView(Class view) {
        Intent intent = new Intent(this,view);
        startActivity(intent);
    }

    @Override
    public void setNotification(Enum e) {

    }

    public void init()
    {
        this.productDetailPresenter = new ProductDetailPresenter(this);
        this.thumbnail = (ImageView) findViewById(R.id.thumbnail);
        this.name = (TextView) findViewById(R.id.name);
        this.price = (TextView) findViewById(R.id.price);
        this.description = (TextView) findViewById(R.id.description);
        this.addToCart = (Button) findViewById(R.id.addtocart);
        this.cart = (Button) findViewById(R.id.cart);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void loadData(Product product) {
        this.name.setText(product.getName());
        this.price.setText(product.getPrice()+"");
        this.description.setText(product.getDescription());

        Picasso.get().load(product.getThumbnail()).into(this.thumbnail);

    }
}