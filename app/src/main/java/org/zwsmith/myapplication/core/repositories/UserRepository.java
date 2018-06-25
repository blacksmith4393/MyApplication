package org.zwsmith.myapplication.core.repositories;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.zwsmith.myapplication.core.models.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private User currentUser = null;

    @Inject
    public UserRepository() {
    }

    public void createUser(String userId, String displayName, String email) {
        final User newUser = new User(displayName, email);
        final DatabaseReference userReference = databaseReference.child("users").child(userId);

        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User existingUser = dataSnapshot.getValue(User.class);
                    Log.i(TAG, "User already exists: " + existingUser.username);
                } else {
                    userReference
                            .setValue(newUser)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.i(TAG, "Added newUser: " + newUser.email);
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Database error: " + databaseError.getMessage());
            }
        });
    }
}
