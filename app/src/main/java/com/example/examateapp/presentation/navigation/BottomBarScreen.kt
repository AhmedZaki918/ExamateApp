package com.example.examateapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.examateapp.R
import com.example.examateapp.data.local.Constants.CONNECTOR
import com.example.examateapp.data.local.Constants.HOME
import com.example.examateapp.data.local.Constants.PROFILE
import com.example.examateapp.data.local.Constants.QUESTIONS
import com.example.examateapp.data.local.Constants.TOOLS

sealed class BottomBarScreen(
    val route: String,
    val icon: Int
) {

    data object Home : BottomBarScreen(
        route = HOME,
        icon = R.drawable.ic_home
    )

    data object Connector : BottomBarScreen(
        route = CONNECTOR,
        icon = R.drawable.ic_connect
    )

    data object Questions: BottomBarScreen(
        route = QUESTIONS,
        icon = R.drawable.ic_question
    )

    data object Tools : BottomBarScreen(
        route = TOOLS,
        icon = R.drawable.ic_tools
    )

    data object Profile : BottomBarScreen(
        route = PROFILE,
        icon = R.drawable.ic_profile
    )
}