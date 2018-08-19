package org.zwsmith.myapplication.core.interactors

import org.zwsmith.myapplication.core.repositories.UserRepository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateUserInteractor @Inject
constructor(private val userRepository: UserRepository) {

    fun createUser(userId: String, displayName: String, email: String) {
        userRepository.createUser(userId, displayName, email)
    }
}
