package com.example.deluxe.Model;

import com.example.deluxe.Entity.Wallet;
import com.example.deluxe.Interface.Model.DataFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WalletModel {

    DatabaseReference ref;

    public WalletModel(){
        this.ref = FirebaseDatabase.getInstance().getReference().child("wallet");
    }

    public void add(String key, Wallet wallet)
    {
        this.ref.child(key).setValue(wallet);

    }
}
