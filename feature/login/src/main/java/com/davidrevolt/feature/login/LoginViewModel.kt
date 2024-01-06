package com.davidrevolt.feature.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidrevolt.core.data.utils.authentication.AuthenticationService
import com.davidrevolt.core.data.utils.snackbarmanager.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val snackbarManager: SnackbarManager
) : ViewModel() {

    val authState = authenticationService.authState().map{it}
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch {
            try {
                authenticationService.authenticate(email = email, password = password)
                Log.i("AppLog","User Authenticated")
            }catch (e:Exception){
                Log.e("AppLog","${e.message}")
                snackbarManager.snackbarMessage("${e.message}")
            }
        }
    }
}