package com.example.deluxe.View.Account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Interface.PresenterView.Account.AccountInterface;
import com.example.deluxe.Presenter.Account.AccountPresenter;
import com.example.deluxe.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

public class AccountFragment extends Fragment implements AccountInterface.AccountView {
	private final int PICK_IMAGE_REQUEST = 21;
	FirebaseStorage firebaseStorage;
	StorageReference storageReference;
	private ImageView avatar;
	private Uri filePath;
	private AccountInterface.AccountPresenter accountPresenter;

	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
		View v = inflater.inflate(R.layout.fragment_account, container, false);

		this.init(v);


		this.accountPresenter.getAvatar();

		this.avatar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				choosePicture();
			}
		});

		return v;
	}

	public void choosePicture() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "SELECT ANH"), PICK_IMAGE_REQUEST);
	}

	public void init(View v) {
		this.accountPresenter = new AccountPresenter(this);
		this.storageReference = FirebaseStorage.getInstance().getReference().child("images");
		this.avatar = v.findViewById(R.id.avatar);
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
		Picasso.get().load(url).into(avatar);
	}
}