package com.example.androidstudentsapp.model

data class Student(
    var id: String,
    var name: String,
    var isChecked: Boolean = false,
    var avatarUrl: String = "" // For now, we'll use a default image
) 