package com.example.examateapp.presentation.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examateapp.util.questionsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuestionsUiState())
    val uiState: StateFlow<QuestionsUiState> = _uiState.asStateFlow()

    init {
        displayQuestions()
    }

    fun onIntent(intent: QuestionIntent) {
        if (intent is QuestionIntent.ChooseItem) {
            displayQuestions()
            _uiState.update {
                it.copy(
                    questions = it.questions.map { item ->
                        if (intent.id == item.id) {
                            item.copy(
                                isItemActive = true
                            )
                        } else item
                    }
                )
            }
        }
    }

    fun displayQuestions() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    questions = questionsData()
                )
            }
        }
    }
}