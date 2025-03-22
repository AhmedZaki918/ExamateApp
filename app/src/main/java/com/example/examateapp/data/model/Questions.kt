package com.example.examateapp.data.model

data class Questions(
    val questionsDone: String,
    val icon: Int,
    val caption: String,
    val progressBar: Float,
    val progressText: String,
    val id : Byte,
    val isItemActive : Boolean = false
)
