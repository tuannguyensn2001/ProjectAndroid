package com.example.deluxe.View.History;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class StatisticsTabAdapter extends FragmentStateAdapter {

	public StatisticsTabAdapter(@NonNull FragmentActivity fragmentActivity) {
		super(fragmentActivity);
	}

	public StatisticsTabAdapter(@NonNull Fragment fragment) {
		super(fragment);
	}

	public StatisticsTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
		super(fragmentManager, lifecycle);
	}

	@NonNull
	@Override
	public Fragment createFragment(int position) {
		return StatisticsFragment.newInstance(position);
	}

	@Override
	public int getItemCount() {
		return 4;
	}
}
