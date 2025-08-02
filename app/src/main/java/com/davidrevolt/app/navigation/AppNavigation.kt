package com.davidrevolt.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.davidrevolt.app.ui.AppState
import com.davidrevolt.feature.home.HOME_ROUTE
import com.davidrevolt.feature.home.homeScreen
import com.davidrevolt.feature.login.LOGIN_ROUTE
import com.davidrevolt.feature.login.loginScreen
import com.davidrevolt.feature.signup.navigateToSignUp
import com.davidrevolt.feature.signup.signupScreen

@Composable
fun AppNavigation(appState: AppState) {
    val navController = appState.navController
    val startDestination = if (appState.userLoggedIn) HOME_ROUTE else LOGIN_ROUTE

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen(onSignOutClick = appState::signOut)

        signupScreen(onSuccessRegister = appState::onAuthStateChangeNavigation)

        loginScreen(
            onSuccessLogin = appState::onAuthStateChangeNavigation,
            onRegisterClick = navController::navigateToSignUp
        )
    }
}