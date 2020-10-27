package com.example.deluxe.Model;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.AuthLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Auth {
    private static Auth auth = null;

    private FirebaseAuth mAuth;
    private Auth()
    {
        this.mAuth = FirebaseAuth.getInstance();

    }

    public static Auth getInstance()
    {
        if (auth == null){
            auth = new Auth();
        }
        return auth;
    }

    public void attempt(User user, final AuthLogin authFirebase)
    {
        String email = user.getUser();
        String password = user.getPassword();

        this.mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    authFirebase.loginSuccessful();
                } else{
                    authFirebase.loginUnsuccessful();
                }
            }
        });
    }

    public boolean check()
    {
        return mAuth.getCurrentUser() != null;
    }

    public FirebaseUser user()
    {
        return this.check() ? mAuth.getCurrentUser() : null;
    }

    public void logout()
    {
        this.mAuth.signOut();
    }


}
