package com.example.deluxe.Model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WithdrawModel {

    DatabaseReference ref;

    public WithdrawModel()
    {
        this.ref= FirebaseDatabase.getInstance().getReference().child("withdraw");
    }

    public void Withdraw( Withdraw withdraw)
    {

        String key = this.ref.push().getKey();
        this.ref.child(key).setValue(withdraw);

    }
}
