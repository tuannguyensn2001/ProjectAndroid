package com.example.deluxe.Model;

import com.example.deluxe.Entity.Person;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonModel {

    private DatabaseReference ref;

    public  PersonModel()
    {
        this.ref = FirebaseDatabase.getInstance().getReference().child("persons");
    }

    public void created(Person person)
    {
        String key = this.ref.push().getKey();
        this.ref.child(key).setValue(person);
    }
}
