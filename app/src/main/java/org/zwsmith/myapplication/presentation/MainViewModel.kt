package org.zwsmith.myapplication.presentation

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import org.zwsmith.myapplication.core.interactors.CreateUserInteractor
import org.zwsmith.myapplication.core.models.User

import javax.inject.Inject

class MainViewModel @Inject
internal constructor(private val createUserInteractor: CreateUserInteractor) {

    fun createUser() {
        FirebaseAuth.getInstance().currentUser
                ?.let { createUserInteractor.createUser(it) }
                ?: Log.e(TAG, "Error getting current user from FirebaseAuth. Current user = null")
    }

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }
}