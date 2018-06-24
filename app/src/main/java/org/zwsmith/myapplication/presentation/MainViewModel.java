package org.zwsmith.myapplication.presentation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class MainViewModel {
    private MainDataModel mainDataModel;

    @Inject
    MainViewModel(MainDataModel mainDataModel) {
        this.mainDataModel = mainDataModel;
    }

    public void createUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            mainDataModel.createUser(user.getUid(), user.getDisplayName(), user.getEmail());
        }
    }
}