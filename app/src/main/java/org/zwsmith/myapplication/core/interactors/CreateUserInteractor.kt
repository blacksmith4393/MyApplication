package org.zwsmith.myapplication.core.interactors

import com.google.firebase.auth.FirebaseUser
import org.zwsmith.myapplication.core.repositories.UserRepository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateUserInteractor @Inject
constructor(private val userRepository: UserRepository) {

    fun createUser(firebaseUser: FirebaseUser) {
        userRepository.createUser(firebaseUser)
    }
}
