package com.example.examateapp.data.model

data class Suggestions(
    val profile : String,
    val instructor : String,
    val targetLevel : String,
    val lastSeen : String,
    val subjects : List<String>,
    val info : Info
)

data class Info(
    val location : String,
    val gender : String,
    val age : String,
    val date : String
)
