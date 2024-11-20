package com.example.koadex.views


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import kotlinx.coroutines.launch
import com.auth0.android.callback.Callback
import com.auth0.android.result.Credentials


class AuthViewModel(
    private val authenticationClient: AuthenticationAPIClient
) : ViewModel() {

    var loggedIn by mutableStateOf(false)
    var credentials: Credentials? by mutableStateOf(null)
    var errorMessage: String by mutableStateOf("")

    fun login(username: String, password: String) {
        viewModelScope.launch {
            authenticationClient
                .login(username, password, "Username-Password-Authentication")
                .setConnection("Username-Password-Authentication")
                .validateClaims()
                .setScope("openid profile email")
                .start(object : Callback<Credentials, AuthenticationException> {
                    override fun onSuccess(result: Credentials) {
                        credentials = result
                        loggedIn = true
                        errorMessage = ""
                    }

                    override fun onFailure(error: AuthenticationException) {
                        loggedIn = false
                        errorMessage = error.message ?: "Error desconocido"
                    }
                })
        }
    }
}

/*
class AuthViewModel(private val auth0: Auth0) : ViewModel() {
    var loggedIn by mutableStateOf(false)
    var credentials: Credentials? by mutableStateOf(null)
    var errorMessage: String by mutableStateOf("")

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val authentication = AuthenticationAPIClient(auth0)

            authentication
                .login(username, password, "Username-Password-Authentication")
                .setConnection("Username-Password-Authentication")
                .validateClaims()
                .setScope("openid profile email")
                .start(object : Callback<Credentials, AuthenticationException> {
                    override fun onSuccess(result: Credentials) {
                        credentials = result
                        loggedIn = true
                        errorMessage = ""
                    }

                    override fun onFailure(error: AuthenticationException) {
                        loggedIn = false
                        errorMessage = error.getDescription() ?: "Error desconocido" // Cambia a getDescription()
                    }
                })
        }
    }
}
*/
interface AuthRepository {
    fun login(username: String, password: String, callback: Callback<Credentials, AuthenticationException>)
}

class AuthRepositoryImpl(private val auth0: Auth0) : AuthRepository {
    private val authenticationAPIClient = AuthenticationAPIClient(auth0)

    override fun login(username: String, password: String, callback: Callback<Credentials, AuthenticationException>) {
        authenticationAPIClient
            .login(username, password, "Username-Password-Authentication")
            .start(callback)
    }
}
