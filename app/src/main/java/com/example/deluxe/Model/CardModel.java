package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.Card;
import com.example.deluxe.Interface.Model.CardInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CardModel {

	private DatabaseReference ref;

	public CardModel() {
		this.ref = FirebaseDatabase.getInstance().getReference("card");
	}

	public void getListCard(Card card, final CardInterface cardInterface) {


		final String key = card.getKey();
		final String serial = card.getSerial();

		this.ref.orderByChild("serial").equalTo(serial).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				String recentKey = null;
				Card card = null;
				for (DataSnapshot item : snapshot.getChildren()) {
					card = item.getValue(Card.class);
					recentKey = item.getKey();
				}

				if (card != null) {
					if (card.getSerial().equals(serial) && card.getKey().equals(key) && !card.isIs_active()) {
						cardInterface.done(card);
						card.setIs_active(true);
						ref.child(recentKey).setValue(card);
						Log.e("user", "done");
					} else if (card.getSerial().equals(serial) && card.getKey().equals(key) && card.isIs_active()) {
						cardInterface.inValid();
						Log.e("user", "invalid");
					} else {
						cardInterface.failed();
						Log.e("user", "invalid");
					}
				} else {
					Log.e("user", "invalid");
					cardInterface.failed();
				}


			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void add() {
		String key = this.ref.push().getKey();
		Card card = new Card();
		card.setKey("12345678901234");
		card.setSerial("1234567890");
		card.setValue(30000);
		this.ref.child(key).setValue(card);
	}

}
