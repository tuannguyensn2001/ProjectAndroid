package com.example.deluxe.Adapter.StatisticsCard;

import android.os.Parcel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class StatisticsCardDad extends ExpandableGroup<StatisticsCardChild> {

	public StatisticsCardDad(String title, List<StatisticsCardChild> items) {
		super(title, items);
	}

	protected StatisticsCardDad(Parcel in) {
		super(in);
	}
}
