package com.example.deluxe.Model;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Entity.Wallet;
import com.example.deluxe.Interface.Model.AuthLogin;
import com.example.deluxe.Interface.Model.AuthSignUp;
import com.example.deluxe.Interface.Model.UserDetailsInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Date;

public class Auth {
	private static Auth auth = null;
	private FirebaseAuth mAuth;
	private UserModel userModel;
	private WalletModel walletModel;

	private Auth() {
		this.mAuth = FirebaseAuth.getInstance();
		this.userModel = new UserModel();
		this.walletModel = new WalletModel();
	}

	public static Auth getInstance() {
		if (auth == null) {
			auth = new Auth();
		}
		return auth;
	}

	public void attempt(User user, final AuthLogin authFirebase) {
		String email = user.getUser();
		String password = user.getPassword();

		this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (task.isSuccessful()) {
					new UserModel().show(Auth.getInstance().user().getUid(), new UserDetailsInterface() {
						@Override
						public void dataIsLoaded(User user) {
							user.setToken(FirebaseInstanceId.getInstance().getToken());
							FirebaseDatabase.getInstance().getReference().child("user").child(Auth.getInstance().user().getUid()).setValue(user);
						}
					});
					authFirebase.loginSuccessful();
				} else {
					authFirebase.loginUnsuccessful();
				}
			}
		});
	}

	public boolean check() {
		return mAuth.getCurrentUser() != null;
	}

	public FirebaseUser user() {
		return this.check() ? mAuth.getCurrentUser() : null;
	}

	public void logout() {
		this.mAuth.signOut();
	}

	public void signUp(final User user, final AuthSignUp authSignUp) {
		String email = user.getEmail();
		String password = user.getPassword();
		this.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (task.isSuccessful()) {
					String key = Auth.getInstance().user().getUid();
					user.setCreated_at(new Date().toString());
					user.setUpdated_at(new Date().toString());
					user.setAvatar("https://firebasestorage.googleapis.com/v0/b/projectandroid-8d413.appspot.com/o/avatar%2Fdefault-avatar.png?alt=media&token=e7aa4b47-8849-4474-a722-fd67466a1519");
					userModel.create(user, key);

					Wallet wallet = new Wallet(0);
					wallet.setCreated_at(new Date().toString());
					wallet.setUpdated_at(new Date().toString());

					walletModel.add(key, wallet);


					authSignUp.signUpSuccessful();
				} else {
					authSignUp.signUpUnSuccessful();
				}
			}
		});
	}

	public void update() {

	}

}
