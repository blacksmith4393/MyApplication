package org.zwsmith.myapplication.core.repositories

import com.firebase.ui.auth.AuthUI

import java.util.Collections

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepository @Inject constructor() {
    private val googleIdpConfig = AuthUI.IdpConfig.GoogleBuilder().build()
    private val authProviders: List<AuthUI.IdpConfig> = listOf(googleIdpConfig)
}
