package com.davidrevolt.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davidrevolt.app.R
import com.davidrevolt.app.navigation.AppNavigation
import com.davidrevolt.core.data.utils.authentication.AuthenticationService
import com.davidrevolt.core.designsystem.components.rememberSystemUiController

@Composable
fun App(
    authenticationService: AuthenticationService,
    appState: AppState = rememberAppState(authenticationService = authenticationService)
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.systemBarsDarkContentEnabled = false

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
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
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
