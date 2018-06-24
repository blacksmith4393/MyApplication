package org.zwsmith.myapplication.core.interactors;

import org.zwsmith.myapplication.core.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreateUserInteractor {
    private final UserRepository userRepository;

    @Inject
    public CreateUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String userId, String displayName, String email) {
        userRepository.createUser(userId, displayName, email);
    }
}
