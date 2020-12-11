package com.example.deluxe.Entity;

import android.widget.FrameLayout;

public class ListViewInfo {

	private String title;
	private String subtitle;
	private FrameLayout frameLayout;

	public ListViewInfo(String title, String subtitle, FrameLayout frameLayout) {
		this.title = title;
		this.subtitle = subtitle;
		this.frameLayout = frameLayout;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public FrameLayout getFrameLayout() {
		return frameLayout;
	}

	public void setFrameLayout(FrameLayout frameLayout) {
		this.frameLayout = frameLayout;
	}
}
