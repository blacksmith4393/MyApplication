package org.zwsmith.myapplication.core.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

import org.zwsmith.myapplication.core.models.User

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.Exception

@Singleton
class UserRepository @Inject constructor() {
    private val databaseReference = FirebaseFirestore.getInstance()

    private val currentUser: User? = null

    fun createUser(firebaseUser: FirebaseUser) {
        val user = hashMapOf<String, Any?>()
                .apply {
                    put(DISPLAY_NAME_KEY, firebaseUser.displayName)
                    put(EMAIL_KEY, firebaseUser.email)
                    put(WORKOUT_COUNT, 0)
                }

        databaseReference.collection(USERS_COLLECTION_KEY)
                .document(firebaseUser.uid)
                .set(user)
                .addOnSuccessListener {
                    Log.i(TAG, "New user added")
                }
                .addOnFailureListener { e: Exception ->
                    Log.e(TAG, "Error creating user: ${e.message}", e)
                }
    }

    companion object {
        private val TAG = UserRepository::class.java.simpleName
        private const val DISPLAY_NAME_KEY = "display_name"
        private const val EMAIL_KEY = "email"
        private const val WORKOUT_COUNT = "workout_count"
        private const val USERS_COLLECTION_KEY = "users"
    }
}
