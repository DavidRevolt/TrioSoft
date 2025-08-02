package com.davidrevolt.core.data.utils.authentication

import kotlinx.coroutines.flow.Flow


interface AuthenticationService {
    val currentUserId: String
    val userLoggedIn: Boolean
    /**
     * @Return Flow<Boolean> if user is logged in
     * */
    fun authState(): Flow<Boolean>
    suspend fun authenticate(email: String, password: String)
    suspend fun register(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun deleteAccount()
    suspend fun signOut()
}