package com.example.examateapp.util

import com.example.examateapp.R
import com.example.examateapp.data.model.TabItem
import com.example.examateapp.data.model.TabItemQuestion

val tabItems = listOf(
    TabItem(
        title = "Suggestions"
    ),
    TabItem(
        title = "Chat"
    )
)

val tabQuestionsItems = listOf(
    TabItemQuestion(
        title = "Writing",
        icon = R.drawable.ic_pencil
    ),
    TabItemQuestion(
        title = "Oral",
        icon = R.drawable.ic_mic
    )
)