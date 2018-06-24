package org.zwsmith.myapplication.core.repositories;

import com.firebase.ui.auth.AuthUI;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AuthenticationRepository {
    private AuthUI.IdpConfig googleIdpConfig = new AuthUI.IdpConfig.GoogleBuilder().build();
    private List<AuthUI.IdpConfig> authProviders =
            Collections.singletonList(googleIdpConfig);

    @Inject
    AuthenticationRepository() {}

}
