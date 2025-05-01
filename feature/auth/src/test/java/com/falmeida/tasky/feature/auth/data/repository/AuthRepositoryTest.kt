package com.falmeida.tasky.feature.auth.data.repository

import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.domain.isFailure
import com.falmeida.tasky.core.domain.isSuccess
import com.falmeida.tasky.core.model.AuthResponse
import com.falmeida.tasky.core.model.LoginRequest
import com.falmeida.tasky.core.model.RegisterRequest
import com.falmeida.tasky.core.network.auth.AuthApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AuthRepositoryTest {

    private lateinit var authApi: AuthApi
    private lateinit var repository: AuthRepository

    @Before
    fun setup() {
        authApi = mockk()
        repository = AuthRepository(authApi)
    }

    @Test
    fun `login returns TaskyResult Success`() = runTest {
        val expectedBody = AuthResponse(token = "abc123")
        val expectedResponse = Response.success(expectedBody)
        ArrangeBuilder()
            .withLoginResponse(expectedResponse)
            .build()

        val result = repository.login("test@test.com", "password")

        assertTrue(result.isSuccess())
        assertEquals(expectedBody, (result as TaskyResult.Success).data)
        coVerify { authApi.login(LoginRequest("test@test.com", "password")) }
    }

    @Test
    fun `login returns failure when API response is unsuccessful`() = runTest {
        val errorBody = "Error".toResponseBody(null)
        val errorResponse = Response.error<AuthResponse>(400, errorBody)

        ArrangeBuilder()
            .withLoginResponse(errorResponse)
            .build()

        val result = repository.login("test@test.com", "password")

        assertTrue(result.isFailure())
        coVerify { authApi.login(any()) }
    }

    @Test
    fun `register returns success when API call is successful`() = runTest {
        val response = Response.success(Unit)
        ArrangeBuilder()
            .withRegisterResponse(response)
            .build()

        val result = repository.register(RegisterRequest("Name", "email@test.com", "pass123"))

        assertTrue(result.isSuccess())
        coVerify { authApi.register(any()) }
    }

    @Test
    fun `register returns failure when API response is unsuccessful`() = runTest {
        val errorBody = "Error".toResponseBody("application/json".toMediaType())
        ArrangeBuilder()
            .withRegisterResponse(Response.error(400, errorBody))
            .build()

        val result = repository.register(RegisterRequest("Name", "email@test.com", "pass123"))

        assertTrue(result.isFailure())
        coVerify { authApi.register(any()) }
    }


    @Test
    fun `register returns failure when exception is thrown`() = runTest {
        ArrangeBuilder()
            .withError()
            .build()

        val result = repository.register(RegisterRequest("Name", "email@test.com", "pass123"))

        assertTrue(result.isFailure())
        coVerify { authApi.register(any()) }
    }

    private inner class ArrangeBuilder {
        private var loginResponse: Response<AuthResponse>? = null
        private var registerResponse: Response<Unit>? = null
        private var shouldThrow = false

        fun withLoginResponse(response: Response<AuthResponse>): ArrangeBuilder = apply {
            loginResponse = response
        }

        fun withRegisterResponse(response: Response<Unit>): ArrangeBuilder = apply {
            registerResponse = response
        }

        fun withError(): ArrangeBuilder = apply {
            shouldThrow = true
        }

        fun build() {
            if (loginResponse != null) {
                coEvery { authApi.login(any()) } returns loginResponse!!
            }

            if (registerResponse != null) {
                coEvery { authApi.register(any()) } returns registerResponse!!
            }

            if (shouldThrow) {
                coEvery { authApi.register(any()) } throws Exception("Network error")
            }
        }
    }
}
