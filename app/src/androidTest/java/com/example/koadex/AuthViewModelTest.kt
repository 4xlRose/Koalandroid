package com.example.koadex

import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.result.Credentials
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import com.example.koadex.Views.AuthViewModel
import junit.framework.TestCase.assertTrue
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import com.auth0.android.callback.Callback
import com.example.koadex.Views.AuthRepository
import junit.framework.TestCase.assertFalse
import org.junit.Rule
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class AuthViewModelTest {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var authenticationAPIClient: AuthenticationAPIClient

    @Before
    fun setup() {
        // Inicialización de mocks
        MockKAnnotations.init(this)

        // Mock de AuthenticationAPIClient
        authenticationAPIClient = mockk()

        // Inicializar el ViewModel con el mock de AuthenticationAPIClient
        authViewModel = AuthViewModel(authenticationAPIClient)
    }

    @Test
    fun testLoginSuccess(): Unit = runTest {
        val mockCredentials = mockk<Credentials>()

        every { authenticationAPIClient.login(any(), any(), any()).start(any()) } answers {
            val callback = secondArg<Callback<Credentials, AuthenticationException>>()
            callback.onSuccess(mockCredentials)
        }

        authViewModel.login("a01198418@tec.mx", "k0@landro1d")

        assertTrue(authViewModel.loggedIn)
        assertEquals(mockCredentials, authViewModel.credentials)
        assertEquals("", authViewModel.errorMessage)
    }

    @Test
    fun testLoginFailure(): Unit = runTest {
        val mockError = mockk<AuthenticationException>()
        every { mockError.message } returns "Invalid credentials"

        every { authenticationAPIClient.login(any(), any(), any()).start(any()) } answers {
            val callback = secondArg<Callback<Credentials, AuthenticationException>>()
            callback.onFailure(mockError)
        }
        authViewModel.login("a01198418@tec.mx", "No-k0@landro1d")

        assertFalse(authViewModel.loggedIn)
        assertEquals("Invalid credentials", authViewModel.errorMessage)
    }
}

