package com.example.deluxe.Adapter.StatisticsCard;

import android.view.View;
import android.widget.TextView;

import com.example.deluxe.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class StatisticsCardDadViewHolder extends GroupViewHolder {
	private TextView month;

	public StatisticsCardDadViewHolder(View itemView) {
		super(itemView);

		month = itemView.findViewById(R.id.title);
	}

	@Override
	public void expand() {
		super.expand();
	}

	@Override
	public void collapse() {
		super.collapse();
	}

	public void setGroupName(ExpandableGroup group) {
		month.setText(group.getTitle());
	}
}
