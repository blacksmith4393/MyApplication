package org.zwsmith.myapplication.core.repositories;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.zwsmith.myapplication.core.models.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Inject
    public UserRepository() {}

    public void createUser(String userId, String displayName, String email) {
        User user = new User(displayName, email);
        databaseReference.child("users").child(userId).setValue(user);
    }
}
