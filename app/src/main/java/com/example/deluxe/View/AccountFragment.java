package com.example.deluxe.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.R;

public class AccountFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
		return inflater.inflate(R.layout.fragment_account,container, false);
	}
}
