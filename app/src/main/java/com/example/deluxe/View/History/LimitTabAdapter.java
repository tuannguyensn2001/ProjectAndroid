package com.example.deluxe.View.History;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LimitTabAdapter extends FragmentStateAdapter {
	public LimitTabAdapter(@NonNull FragmentActivity fragmentActivity) {
		super(fragmentActivity);
	}

	@NonNull
	@Override
	public Fragment createFragment(int position) {
		return StatisticsFragment.newInstance(position + 4);
	}

	@Override
	public int getItemCount() {
		return 2;
	}
}
