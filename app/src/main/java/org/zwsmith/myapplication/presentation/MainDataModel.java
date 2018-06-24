package org.zwsmith.myapplication.presentation;

import org.zwsmith.myapplication.core.interactors.CreateUserInteractor;

import javax.inject.Inject;

public class MainDataModel {
    private CreateUserInteractor createUserInteractor;

    @Inject
    public MainDataModel(CreateUserInteractor createUserInteractor) {
        this.createUserInteractor = createUserInteractor;
    }

    public void createUser(String userId, String displayName, String email) {
        createUserInteractor.createUser(userId, displayName, email);
    }
}
