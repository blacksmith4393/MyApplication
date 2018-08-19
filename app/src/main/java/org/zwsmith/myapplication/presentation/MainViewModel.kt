package org.zwsmith.myapplication.presentation

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import org.zwsmith.myapplication.core.interactors.CreateUserInteractor

import javax.inject.Inject

class MainViewModel @Inject
internal constructor(private val createUserInteractor: CreateUserInteractor) {

    fun createUser() {
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    }
}