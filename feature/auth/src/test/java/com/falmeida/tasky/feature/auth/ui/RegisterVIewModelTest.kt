import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.feature.auth.domain.usecase.RegisterUseCase
import com.falmeida.tasky.feature.auth.domain.validator.IAuthValidator
import com.falmeida.tasky.feature.auth.ui.register.RegisterAction
import com.falmeida.tasky.feature.auth.ui.register.RegisterEffect
import com.falmeida.tasky.feature.auth.ui.register.RegisterViewModel
import com.falmeida.testing.rules.MainDispatcherRule
import com.falmeida.testing.rules.TestDispatcherProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RegisterViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var registerUseCase: RegisterUseCase

    @MockK
    lateinit var validator: IAuthValidator

    private val testDispatcherProvider = TestDispatcherProvider(dispatcherRule.dispatcher)

    lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = RegisterViewModel(validator, registerUseCase, testDispatcherProvider)
    }

    @Test
    fun `submit with valid data emits NavigateToHome`() = runTest {
        val builder = ArrangeBuilder()
            .withValidName(true)
            .withValidEmail(true)
            .withValidPassword(true)
            .withRegisterResult(TaskyResult.Success(Unit))
            .build()

        builder.viewModel.onAction(RegisterAction.EnteredName("Fernanda"))
        builder.viewModel.onAction(RegisterAction.EnteredEmail("fernanda@test.com"))
        builder.viewModel.onAction(RegisterAction.EnteredPassword("Password1"))
        builder.viewModel.onAction(RegisterAction.Submit)
        advanceUntilIdle() // ensures all coroutines complete

        val result = viewModel.effect.first()
        assertTrue(result is RegisterEffect.NavigateToHome)
    }

    @Test
    fun `submit with invalid form shows error`() = runTest {
        val builder = ArrangeBuilder()
            .withValidName(false)
            .withValidEmail(false)
            .withValidPassword(false)
            .build()

        builder.viewModel.onAction(RegisterAction.EnteredName("A"))
        builder.viewModel.onAction(RegisterAction.EnteredEmail("invalid"))
        builder.viewModel.onAction(RegisterAction.EnteredPassword("123"))

        builder.viewModel.onAction(RegisterAction.Submit)

        val result = builder.viewModel.effect.first()
        assertTrue(result is RegisterEffect.ShowError)
    }

    private inner class ArrangeBuilder {
        private var isNameValid = true
        private var isEmailValid = true
        private var isPasswordValid = true
        private var registerResult: TaskyResult<Unit> = TaskyResult.Success(Unit)

        lateinit var viewModel: RegisterViewModel

        fun withValidName(valid: Boolean) = apply {
            isNameValid = valid
        }

        fun withValidEmail(valid: Boolean) = apply {
            isEmailValid = valid
        }

        fun withValidPassword(valid: Boolean) = apply {
            isPasswordValid = valid
        }

        fun withRegisterResult(result: TaskyResult<Unit>) = apply {
            registerResult = result
        }

        fun build(): ArrangeBuilder {
            every { validator.isValidName(any()) } returns isNameValid
            every { validator.isValidEmail(any()) } returns isEmailValid
            every { validator.isValidPassword(any()) } returns isPasswordValid
            coEvery { registerUseCase(any()) } returns registerResult

            viewModel = RegisterViewModel(validator, registerUseCase, testDispatcherProvider)
            return this
        }
    }
}