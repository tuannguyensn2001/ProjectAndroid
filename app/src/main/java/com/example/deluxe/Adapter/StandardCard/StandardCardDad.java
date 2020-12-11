package com.example.deluxe.Adapter.StandardCard;

import android.os.Parcel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class StandardCardDad extends ExpandableGroup<StandardCardChild> {
	public StandardCardDad(String title, List<StandardCardChild> items) {
		super(title, items);
	}

	protected StandardCardDad(Parcel in) {
		super(in);
	}
}
