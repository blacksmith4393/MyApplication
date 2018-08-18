package org.zwsmith.myapplication.core.models

class User {
    var username: String
    var email: String
    var workoutCount: Int = 0

    constructor() {}

    constructor(username: String, email: String) {
        this.username = username
        this.email = email
    }
}
