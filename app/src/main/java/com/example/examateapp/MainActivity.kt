package com.example.examateapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examateapp.data.local.Constants.CONNECTOR
import com.example.examateapp.data.local.Constants.HOME
import com.example.examateapp.data.local.Constants.QUESTIONS
import com.example.examateapp.data.local.Constants.TOOLS
import com.example.examateapp.presentation.navigation.BottomBarScreen
import com.example.examateapp.presentation.navigation.NavGraph
import com.example.examateapp.ui.theme.ExamateAppTheme
import com.example.examateapp.ui.theme.OffWhite
import com.example.examateapp.ui.theme.Turquoise


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamateAppTheme {
                ChangeSystemBarColor()
                MainUi()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainUi() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavGraph(navController = navController)
    }
}


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Connector,
        BottomBarScreen.Questions,
        BottomBarScreen.Tools,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AnimatedVisibility(visible = true,
        content = {
            NavigationBar(
                containerColor = OffWhite,
                contentColor = Color.Transparent,
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        })
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    NavigationBarItem(
        label = {
            Text(
                text = when (screen.route) {
                    HOME -> stringResource(id = R.string.home)
                    CONNECTOR -> stringResource(id = R.string.connector)
                    QUESTIONS -> stringResource(id = R.string.questions)
                    TOOLS -> stringResource(id = R.string.tools)
                    else -> stringResource(id = R.string.profile)
                }
            )
        },
        alwaysShowLabel = true,
        icon = {
            Icon(
                painter = painterResource(id = screen.icon) ,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Turquoise,
            selectedTextColor = Turquoise,
            indicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ChangeSystemBarColor() {
    val context = LocalContext.current
    if (context is Activity) {
        SideEffect {
            context.window.navigationBarColor = OffWhite.toArgb()
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    ExamateAppTheme {
        MainUi()
    }
}