package com.falmeida.tasky.feature.auth.ui

import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.model.AuthResponse
import com.falmeida.tasky.core.model.LoginRequest
import com.falmeida.tasky.core.model.RegisterRequest
import com.falmeida.tasky.feature.auth.domain.repository.IAuthRepository
import com.falmeida.tasky.feature.auth.domain.usecase.RegisterUseCase
import com.falmeida.tasky.feature.auth.domain.validator.IAuthValidator
import com.falmeida.tasky.feature.auth.ui.register.RegisterAction
import com.falmeida.tasky.feature.auth.ui.register.RegisterEffect
import com.falmeida.tasky.feature.auth.ui.register.RegisterViewModel
import com.falmeida.testing.rules.MainDispatcherRule
import com.falmeida.testing.rules.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: RegisterViewModel
    private lateinit var fakeValidator: FakeValidator
    private lateinit var fakeRepository: FakeAuthRepository
    private lateinit var registerUseCase: RegisterUseCase

    @Before
    fun setUp() {
        fakeValidator = FakeValidator()
        fakeRepository = FakeAuthRepository()
        registerUseCase = RegisterUseCase(fakeRepository)

        viewModel = RegisterViewModel(
            validator = fakeValidator,
            registerUseCase = registerUseCase,
            dispatcherProvider = TestDispatcherProvider(dispatcherRule.dispatcher)
        )
    }

    @Test
    fun `submit with valid data emits NavigateToHome`() = runTest {
        // Arrange
        fakeValidator.apply {
            isNameValid = true
            isEmailValid = true
            isPasswordValid = true
        }
        fakeRepository.registerResult = TaskyResult.Success(Unit)

        // Act
        viewModel.onAction(RegisterAction.EnteredName("Fernanda"))
        viewModel.onAction(RegisterAction.EnteredEmail("fernanda@test.com"))
        viewModel.onAction(RegisterAction.EnteredPassword("Password1"))
        viewModel.onAction(RegisterAction.Submit)

        // Assert
        val effect = viewModel.effect.first()
        assertTrue(effect is RegisterEffect.NavigateToHome)
    }

    @Test
    fun `submit with invalid form emits ShowError`() = runTest {
        // Arrange
        fakeValidator.apply {
            isNameValid = false
            isEmailValid = false
            isPasswordValid = false
        }

        // Act
        viewModel.onAction(RegisterAction.EnteredName("A"))
        viewModel.onAction(RegisterAction.EnteredEmail("bad"))
        viewModel.onAction(RegisterAction.EnteredPassword("123"))
        viewModel.onAction(RegisterAction.Submit)

        // Assert
        val effect = viewModel.effect.first()
        assertTrue(effect is RegisterEffect.ShowError)
    }
}

// ------------------------------------
// ✅ Fake implementations for testing
// ------------------------------------

class FakeValidator : IAuthValidator {
    var isNameValid = true
    var isEmailValid = true
    var isPasswordValid = true

    override fun isValidName(name: String) = isNameValid
    override fun isValidEmail(email: String) = isEmailValid
    override fun isValidPassword(password: String) = isPasswordValid
}

class FakeAuthRepository : IAuthRepository {
    var registerResult: TaskyResult<Unit> = TaskyResult.Success(Unit)

    override suspend fun login(loginRequest: LoginRequest): TaskyResult<AuthResponse> {
        throw NotImplementedError("Not needed for this test")
    }

    override suspend fun register(request: RegisterRequest): TaskyResult<Unit> {
        return registerResult
    }
}