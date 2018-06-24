package org.zwsmith.myapplication.core.models;

public class User {
    public String username;
    public String email;
    public int workoutCount;

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
