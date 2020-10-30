package com.example.deluxe.Model;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.Card;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.CardInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CardModel {

    private DatabaseReference ref;

    public CardModel()
    {
        this.ref = FirebaseDatabase.getInstance().getReference().child("card");
    }

    public void getListCard(Card card, final CardInterface cardInterface)
    {
        String key = card.getKey();
        final String serial = card.getSerial();

        DatabaseReference newref = FirebaseDatabase.getInstance().getReference().child("card/"+key);
        newref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Card card = snapshot.getValue(Card.class);
                if (card  == null)
                cardInterface.failed();
                else
                {
                    Log.e("card",card.getSerial());
                    if (card.getSerial().equals(serial))
                        cardInterface.done(card);
                    else
                        cardInterface.failed();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
