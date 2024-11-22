package com.example.koadex
/*
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.result.Credentials
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import com.auth0.android.callback.Callback
import com.example.koadex.views.AuthRepository
import com.example.koadex.views.AuthViewModel
import io.mockk.MockKAnnotations
import junit.framework.TestCase.assertTrue
import io.mockk.*
import io.mockk.mockk
import io.mockk.every
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.test.runTest

class AuthViewModelTest {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var authenticationAPIClient: AuthenticationAPIClient

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        authenticationAPIClient = mockk()
        authViewModel = AuthViewModel(authenticationAPIClient)
    }

    @Test
    fun testLoginSuccess() = runTest {
        // Simulación para caso de éxito
        val mockCredentials = mockk<Credentials>()
        every { authenticationAPIClient.login(any(), any(), any()).start(any()) } answers {
            val callback = firstArg<Callback<Credentials, AuthenticationException>>()
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
    fun testLoginFailure() = runTest {
        // Simulación para caso de fallo
        val mockError = mockk<AuthenticationException>()
        every { mockError.message } returns "Invalid credentials"
        every { authenticationAPIClient.login(any(), any(), any()).start(any()) } answers {
            val callback = firstArg<Callback<Credentials, AuthenticationException>>()
            callback.onFailure(mockError)
        }

        // Act
        authViewModel.login("user@example.com", "wrongpassword")

        // Assert
        assertFalse(authViewModel.loggedIn)
        assertEquals("Invalid credentials", authViewModel.errorMessage)
    }
}


class AuthViewModelTest {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var auth0: Auth0
    private lateinit var authenticationAPIClient: AuthenticationAPIClient

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        // Mocks para Auth0 y AuthenticationAPIClient
        auth0 = mockk()
        authenticationAPIClient = mockk()

        // Configuración manual para instanciar AuthenticationAPIClient
        every { auth0.clientId } returns "YOUR_CLIENT_ID"
        every { auth0.domain } returns "YOUR_AUTH0_DOMAIN"
        every { AuthenticationAPIClient(auth0) } returns authenticationAPIClient

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
        every { mockError.message } returns "Invalid credentials"
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
*/

