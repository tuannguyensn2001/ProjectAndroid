package com.example.deluxe.Entity;

import android.widget.FrameLayout;

import com.example.deluxe.Core.View;

public class StandardCard {
	private boolean isSeeMoreButton;
	private Class<? extends View> targetActivity;

	private String title;
	private String description;
	private String content;
	private FrameLayout contentDisplay;

	public StandardCard(boolean isSeeMoreButton, Class<? extends View> targetActivity,
						String title, String description, String content, FrameLayout contentDisplay) {
		this.isSeeMoreButton = isSeeMoreButton;
		this.targetActivity = targetActivity;
		this.title = title;
		this.description = description;
		this.content = content;
		this.contentDisplay = contentDisplay;
	}

	public boolean isSeeMoreButton() {
		return isSeeMoreButton;
	}

	public void setSeeMoreButton(boolean seeMoreButton) {
		isSeeMoreButton = seeMoreButton;
	}

	public Class<? extends View> getTargetActivity() {
		return targetActivity;
	}

	public void setTargetActivity(Class<? extends View> targetActivity) {
		this.targetActivity = targetActivity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FrameLayout getContentDisplay() {
		return contentDisplay;
	}

	public void setContentDisplay(FrameLayout contentDisplay) {
		this.contentDisplay = contentDisplay;
	}
}
