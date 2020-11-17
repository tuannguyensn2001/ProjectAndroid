package com.example.deluxe.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Presenter.MainPresenter;
import com.example.deluxe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements MainInterface.MainView {

	private MainInterface.MainPresenter mainPresenter;
	private Button logoutButton, depositButton, transferButton, ruttienButton;

	private Uri filePath;

	private final int PICK_IMAGE_REQUEST = 22;

	FirebaseStorage firebaseStorage;
	StorageReference storageReference;


	private ImageView profilePic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		this.logoutButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mainPresenter.handleLogOut();
			}
		});
		this.depositButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mainPresenter.handleDeposit();
			}
		});

		this.transferButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(SearchUserActivity.class);
			}
		});

		this.ruttienButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(WithdrawActivity.class);
			}
		});

		this.profilePic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				choosePicture();
			}
		});


	}

	private void choosePicture() {

		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Image from here..."),PICK_IMAGE_REQUEST);

	}

	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
		{
			filePath = data.getData();
			profilePic.setImageURI(filePath);

			UploadImage();
		}
	}

	public void UploadImage()
	{

		final ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("Uploading...");
		pd.show();

		final String randomKey = UUID.randomUUID().toString();

		Log.e("image",randomKey);
		final StorageReference riversRef = storageReference.child("images/" + randomKey);

		riversRef.putFile(filePath)
				.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
					@Override
					public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
						// Get a URL to the uploaded content
						pd.dismiss();

						riversRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
							@Override
							public void onComplete(@NonNull Task<Uri> task) {
								Log.e("url",task.getResult().toString()+" ");
							}
						});
						Snackbar.make(findViewById(android.R.id.content),"Image Uploaded.", Snackbar.LENGTH_LONG).show();

					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception exception) {
						// Handle unsuccessful uploads
						// ...
						pd.dismiss();

						Toast.makeText(getApplicationContext(),"Failed To Upload" , Toast.LENGTH_LONG).show();
					}
				})

				.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
					@Override
					public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
						double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
						pd.setMessage("Percent " + (int) progressPercent);
					}
				});



	}

	private void init() {
		this.mainPresenter = new MainPresenter(this);
		this.logoutButton = findViewById(R.id.logoutButton);
		this.depositButton = findViewById(R.id.NapThe);
		this.transferButton = findViewById(R.id.transfer);
		this.ruttienButton = findViewById(R.id.ruttien);

		this.profilePic = findViewById(R.id.profilePicture);

		firebaseStorage = FirebaseStorage.getInstance();

		storageReference = firebaseStorage.getReference();



	}

	@Override
	public void loadView(Class view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
		finish();
	}

	@Override
	public void setNotification(Enum e) {

	}
}
