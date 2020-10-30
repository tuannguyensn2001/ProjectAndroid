package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Entity.Wallet;
import com.example.deluxe.Interface.Model.AuthLogin;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.deluxe.Interface.Model.AuthSignUp;

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
					authFirebase.loginSuccessful();
				} else {
					Log.e("result",task.getException().getMessage());
					authFirebase.loginUnsuccessful();
				}
			}
		}).addOnCanceledListener(new OnCanceledListener() {
			@Override
			public void onCanceled() {
				authFirebase.canceled();
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
					userModel.create(user,key);

					Wallet wallet = new Wallet(0,new Date().toString());
					walletModel.add(key,wallet);

					authSignUp.signUpSuccessful();
				} else {
					authSignUp.signUpunSuccessful();
				}
			}
		});
	}

}

