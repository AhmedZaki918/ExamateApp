package com.example.examateapp.presentation.questions

sealed class QuestionIntent {
    data class ChooseItem(val id: Byte) : QuestionIntent()
}