package com.example.deluxe.Adapter.StandardCard;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.deluxe.Entity.StandardCard;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class StandardCardChildViewHolder extends ChildViewHolder {
	private View view;

	public StandardCardChildViewHolder(View itemView) {
		super(itemView);

		this.view = itemView;
	}

	public void onBind(StandardCard standardCard) {
//		Lay cac thuoc tinh dau vao
		boolean isSeeMoreButton = standardCard.isSeeMoreButton();
		final Class<? extends com.example.deluxe.Core.View> targetActivity = standardCard.getTargetActivity();
		String title = standardCard.getTitle();
		String description = standardCard.getDescription();
		String content = standardCard.getContent();
		FrameLayout contentDisplay = standardCard.getContentDisplay();


//		Tim cac thanh phan trong view
		TextView cardTitle = view.findViewById(R.id.card_title);
		TextView cardDescription = view.findViewById(R.id.card_description);
		TextView cardContent = view.findViewById(R.id.card_content);
		FrameLayout cardDisplayView = view.findViewById(R.id.card_display_view);


		if (!isSeeMoreButton) {
//			Dat noi dung
			cardTitle.setText(title);
			cardDescription.setText(description);
			cardContent.setText(content);
//			cardDisplayView chua biet

			if (description == null) cardDescription.setVisibility(View.GONE);
			if (content == null) cardContent.setVisibility(View.GONE);
			if (contentDisplay == null) cardDisplayView.setVisibility(View.GONE);

		} else {
			cardDescription.setVisibility(View.GONE);
			cardContent.setVisibility(View.GONE);
			cardDisplayView.setVisibility(View.GONE);

			cardTitle.setPadding(0, 0, 0, 0);
			cardTitle.setText(title);
			view.setClickable(true);
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((MainActivity) view.getContext()).loadView(targetActivity);
				}
			});
		}
	}
}
