package com.davidrevolt.feature.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val SIGNUP_ROUTE = "signup_route"

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    this.navigate(SIGNUP_ROUTE, navOptions)
}

fun NavGraphBuilder.signupScreen(onSuccessRegister: () -> Unit) {
    composable(route = SIGNUP_ROUTE) {
        SignUpScreen(onSuccessRegister = onSuccessRegister)
    }
}