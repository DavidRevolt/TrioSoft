package com.davidrevolt.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.davidrevolt.app.R
import com.davidrevolt.app.navigation.AppNavigation
import com.davidrevolt.core.data.utils.authentication.AuthenticationService
import com.davidrevolt.core.data.utils.snackbarmanager.SnackbarManager
import com.davidrevolt.core.designsystem.components.rememberSystemUiController

@Composable
fun App(
    authenticationService: AuthenticationService,
    snackbarManager: SnackbarManager,
    appState: AppState = rememberAppState(
        authenticationService = authenticationService
    )
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.systemBarsDarkContentEnabled = false

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        snackbarManager.message.collect { message ->
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = null,
                duration = SnackbarDuration.Short,
            )
        }
    }



    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_background),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(snackbarHostState, modifier = Modifier.safeDrawingPadding()) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .safeDrawingPadding()
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AppNavigation(appState = appState)
            }
        }
    }
}
