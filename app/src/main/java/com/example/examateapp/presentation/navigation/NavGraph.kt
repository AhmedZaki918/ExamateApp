package com.example.examateapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.examateapp.presentation.connector.ConnectorScreen
import com.example.examateapp.presentation.home.HomeScreen
import com.example.examateapp.presentation.profile.ProfileScreen
import com.example.examateapp.presentation.questions.QuestionsScreen
import com.example.examateapp.presentation.tools.ToolsScreen


@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {

        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }

        composable(route = BottomBarScreen.Connector.route) {
            ConnectorScreen()
        }

        composable(route = BottomBarScreen.Questions.route) {
            QuestionsScreen()
        }

        composable(route = BottomBarScreen.Tools.route) {
            ToolsScreen()
        }


        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
    }
}