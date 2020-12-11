package com.example.deluxe.Adapter.StatisticsCard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deluxe.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class StatisticsCardRecyclerAdapter extends ExpandableRecyclerViewAdapter<StatisticsCardDadViewHolder, StatisticsCardChildViewHolder> {
	public StatisticsCardRecyclerAdapter(List<? extends ExpandableGroup> groups) {
		super(groups);
	}

	@Override
	public StatisticsCardDadViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_statistics_card_dad, parent, false);

		return new StatisticsCardDadViewHolder(view);
	}

	@Override
	public StatisticsCardChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_statistics_card_child, parent, false);

		return new StatisticsCardChildViewHolder(view);
	}

	@Override
	public void onBindChildViewHolder(StatisticsCardChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
		StatisticsCardChild children = ((StatisticsCardDad) group).getItems().get(childIndex);
		holder.onBind(children.getTransaction());
	}

	@Override
	public void onBindGroupViewHolder(StatisticsCardDadViewHolder holder, int flatPosition, ExpandableGroup group) {
		holder.setGroupName(group);
	}
}
