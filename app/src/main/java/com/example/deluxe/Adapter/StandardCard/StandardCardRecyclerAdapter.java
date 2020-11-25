package com.example.deluxe.Adapter.StandardCard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deluxe.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class StandardCardRecyclerAdapter extends ExpandableRecyclerViewAdapter<StandardCardDadViewHolder, StandardCardChildViewHolder> {
	public StandardCardRecyclerAdapter(List<? extends ExpandableGroup> groups) {
		super(groups);
	}

	@Override
	public StandardCardDadViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_standard_card_dad, parent, false);

		return new StandardCardDadViewHolder(view);
	}

	@Override
	public StandardCardChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_standard_card_child, parent, false);

		return new StandardCardChildViewHolder(view);
	}

	@Override
	public void onBindChildViewHolder(StandardCardChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
		StandardCardChild children = ((StandardCardDad) group).getItems().get(childIndex);
		holder.onBind(children.getStandardCard());
	}

	@Override
	public void onBindGroupViewHolder(StandardCardDadViewHolder holder, int flatPosition, ExpandableGroup group) {
		holder.setGroupName((StandardCardDad) group);
	}
}
