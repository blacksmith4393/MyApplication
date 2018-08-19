package org.zwsmith.myapplication.core.models

data class User(
        val displayName: String?,
        val email: String?,
        val workoutCount: Int = 0
)
