package com.davidrevolt.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.davidrevolt.core.data.utils.authentication.AuthenticationService
import com.davidrevolt.feature.home.navigateToHome
import com.davidrevolt.feature.login.navigateToLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberAppState(
    authenticationService: AuthenticationService,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState {
    return remember(
        authenticationService,
        navController,
        coroutineScope
    ) {
        AppState(
            authenticationService,
            navController,
            coroutineScope
        )
    }
}

class AppState(
    private val authenticationService: AuthenticationService,
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope
) {
    val userLoggedIn = authenticationService.userLoggedIn


    fun signOut() {
        coroutineScope.launch {
            authenticationService.signOut()
            onAuthStateChangeNavigation()
        }
    }

    /**
     * Clearing the whole backstack and then navigating to destination according to Auth state.
     * Not logged in -> navigate to Login screen.
     * Logged in -> navigate to Home screen.
     */
    fun onAuthStateChangeNavigation() {
        val navOptions = navOptions {
            popUpTo(0) { inclusive = true }
            launchSingleTop = true

        }
        if (authenticationService.userLoggedIn)
            navController.navigateToHome(navOptions)
        else
            navController.navigateToLogin(navOptions)
    }

}