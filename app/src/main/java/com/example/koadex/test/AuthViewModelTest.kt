package com.example.koadex.Views

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.result.Credentials
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import com.auth0.android.callback.Callback
import com.example.koadex.Views.AuthViewModel
import junit.framework.TestCase.assertTrue
import io.mockk.mockk
import io.mockk.every


class AuthViewModelTest {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var auth0: Auth0
    private lateinit var authenticationAPIClient: AuthenticationAPIClient

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        auth0 = mockk()
        authenticationAPIClient = mockk()

        every { auth0.authenticationAPIClient } returns authenticationAPIClient
        authViewModel = AuthViewModel(auth0)
    }

    @Test
    fun testLoginSuccess(): Unit = runTest {
        // Configuración para el caso de éxito
        val mockCredentials = mockk<Credentials>()
        every { authenticationAPIClient.login(any(), any(), any()).start(any()) } answers {
            val callback = secondArg<Callback<Credentials, AuthenticationException>>()
            callback.onSuccess(mockCredentials)
        }

        // Act
        authViewModel.login("user@example.com", "password123")

        // Assert
        assertTrue(authViewModel.loggedIn)
        assertEquals(mockCredentials, authViewModel.credentials)
        assertEquals("", authViewModel.errorMessage)
    }

    @Test
    fun testLoginFailure(): Unit = runTest {
        // Configuración para el caso de fallo
        val mockError = mockk<AuthenticationException>()
        every { mockError.description } returns "Invalid credentials"
        every { authenticationAPIClient.login(any(), any(), any()).start(any()) } answers {
            val callback = secondArg<Callback<Credentials, AuthenticationException>>()
            callback.onFailure(mockError)
        }

        // Act
        authViewModel.login("user@example.com", "wrongpassword")

        // Assert
        assertFalse(authViewModel.loggedIn)
        assertEquals("Invalid credentials", authViewModel.errorMessage)
    }
}