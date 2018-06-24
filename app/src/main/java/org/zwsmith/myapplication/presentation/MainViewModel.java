package org.zwsmith.myapplication.presentation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.zwsmith.myapplication.core.interactors.CreateUserInteractor;

import javax.inject.Inject;

public class MainViewModel {
    private CreateUserInteractor createUserInteractor;

    @Inject
    MainViewModel(CreateUserInteractor createUserInteractor) {
        this.createUserInteractor = createUserInteractor;
    }

    public void createUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            createUserInteractor.createUser(user.getUid(), user.getDisplayName(), user.getEmail());
        }
    }
}