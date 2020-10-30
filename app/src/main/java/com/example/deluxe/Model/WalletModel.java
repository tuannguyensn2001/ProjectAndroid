package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.Wallet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalletModel {
    private DatabaseReference ref;

    public WalletModel()
    {
        this.ref = FirebaseDatabase.getInstance().getReference().child("wallet");
    }

    public void add(String key, Wallet wallet)
    {
        this.ref.child(key).setValue(wallet);
    }

    public void deposit(final String key, final String value)
    {
        FirebaseDatabase.getInstance().getReference().child("wallet/"+key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Wallet wallet = snapshot.getValue(Wallet.class);
                double valueConveted = Double.parseDouble(value);
                double money = Double.parseDouble(wallet.getAmount());
                wallet.setAmount(money+valueConveted+"");
                Log.e("card",wallet.getAmount()+"");
                FirebaseDatabase.getInstance().getReference().child("wallet").child(key).setValue(wallet);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
