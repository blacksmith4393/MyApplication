package org.zwsmith.myapplication.core.repositories

import android.util.Log

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import org.zwsmith.myapplication.core.models.User

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject
constructor() {
    private val databaseReference = FirebaseDatabase.getInstance().reference

    private val currentUser: User? = null

    fun createUser(userId: String, displayName: String, email: String) {
        val newUser = User(displayName, email)
        val userReference = databaseReference.child("users").child(userId)

        userReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val existingUser = dataSnapshot.getValue<User>(User::class.java!!)
                    Log.i(TAG, "User already exists: " + existingUser!!.username)
                } else {
                    userReference
                            .setValue(newUser)
                            .addOnSuccessListener { Log.i(TAG, "Added newUser: " + newUser.email) }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, "Database error: " + databaseError.message)
            }
        })
    }

    companion object {
        private val TAG = UserRepository::class.java!!.getSimpleName()
    }
}
