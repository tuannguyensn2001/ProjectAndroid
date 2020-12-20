package com.example.deluxe.View.Account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.PresenterView.Account.AccountInterface;
import com.example.deluxe.Presenter.Account.AccountPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.Auth.PasswordChangeActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

public class AccountFragment extends Fragment implements AccountInterface.AccountView {
	private final int PICK_IMAGE_REQUEST = 21;
	private StorageReference storageReference;
	private ImageView avatar1;
	private TextView changeUsernameButton, changePasswordButton;
	private Uri filePath;
	private AccountInterface.AccountPresenter accountPresenter;

	private View view;

	public static AccountFragment newInstance() {
		AccountFragment fragment = new AccountFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
		view = inflater.inflate(R.layout.fragment_account, container, false);

		this.init();

		this.accountPresenter.getAvatar();

		this.avatar1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				choosePicture();
			}
		});
		this.changeUsernameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(UserInformationChange.class);
			}
		});
		this.changePasswordButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(PasswordChangeActivity.class);
			}
		});
		return view;
	}

	public void choosePicture() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "SELECT ANH"), PICK_IMAGE_REQUEST);
	}

	public void init() {
		this.accountPresenter = new AccountPresenter(this);
		this.storageReference = FirebaseStorage.getInstance().getReference().child("images");
		this.avatar1 = view.findViewById(R.id.avatar1);

		view.findViewById(R.id.user_com_bar).setVisibility(View.GONE);
		this.changeUsernameButton = view.findViewById(R.id.user_information_change);
		this.changePasswordButton = view.findViewById(R.id.password_change);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
			filePath = data.getData();

			this.uploadImage();
		}
	}

	public void uploadImage() {
		this.accountPresenter.receiveAvatar(this.filePath);

	}

	@Override
	public void setAvatar(String url) {
		Picasso.get().load(url).into(avatar1);
	}

	@Override
	public void setUserInfo(User user) {
		((TextView) view.findViewById(R.id.username)).setText(user.getUser());
		((TextView) view.findViewById(R.id.email)).setText(user.getEmail());
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(getActivity(), view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}
}