package org.zwsmith.myapplication.presentation;

import javax.inject.Inject;

public class MainViewModel {
    private MainDataModel mainDataModel;

    @Inject
    MainViewModel(MainDataModel mainDataModel) {
        this.mainDataModel = mainDataModel;
    }

    public void createUser(String userId, String displayName, String email) {
        mainDataModel.createUser(userId, displayName, email);
    }
}