package com.bove.martin.adoptapp.presentation.login

import app.cash.turbine.test
import com.bove.martin.adoptapp.TestDispatchers
import com.bove.martin.adoptapp.common.Resource
import com.bove.martin.adoptapp.data.FakeAuthRepository
import com.bove.martin.adoptapp.domain.usecases.EmailLoginUseCase
import com.bove.martin.adoptapp.domain.usecases.GetCurrentUserUseCase
import com.bove.martin.adoptapp.domain.usecases.LogOutUseCase
import com.bove.martin.adoptapp.domain.usecases.LoginWithGoogleUseCase
import com.bove.martin.adoptapp.domain.usecases.RegisterUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Martín Bove on 04/07/2023.
 * E-mail: mbove77@gmail.com
 */
class AuthViewModelTest {

    private lateinit var authViewModel: AuthViewModel

    private lateinit var emailLoginUseCase: EmailLoginUseCase
    private lateinit var registerUseCase: RegisterUseCase
    private lateinit var loginWithGoogleUseCase: LoginWithGoogleUseCase
    private lateinit var getCurrentUserUseCase: GetCurrentUserUseCase
    private lateinit var logOutUseCase: LogOutUseCase

    private lateinit var authRepository: FakeAuthRepository


    @Before
    fun setUp() {
        authRepository = FakeAuthRepository(mockk(relaxed = true))

        emailLoginUseCase = EmailLoginUseCase(authRepository)
        registerUseCase = RegisterUseCase(authRepository)
        loginWithGoogleUseCase = LoginWithGoogleUseCase(authRepository)
        getCurrentUserUseCase = GetCurrentUserUseCase(authRepository)
        logOutUseCase = mockk(relaxed = true)

        authViewModel = AuthViewModel(
            emailLoginUseCase,
            loginWithGoogleUseCase,
            registerUseCase,
            logOutUseCase,
            getCurrentUserUseCase,
            TestDispatchers()
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `if currentUser != null login flow success`() = runTest {
        authViewModel.loginFlow.test {
            val emission = awaitItem()
            assertThat(emission).isInstanceOf(Resource.Success::class.java)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `if login success respond with flow success`() = runTest {
        authViewModel.login("valid@email.com", "1234")

        launch {
            authViewModel.loginFlow.test {
                assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)

                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `if login fail respond with failure flow`() = runTest {
        authRepository.setShouldReturnError(true)
        authViewModel.login("valid@email.com", "1234")

        launch {
            authViewModel.loginFlow.test {
                assertThat(awaitItem()).isInstanceOf(Resource.Failure::class.java)

                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `if register success respond with success flow`() = runTest {
        authViewModel.register("Name", "valid@email.com", "1234")

        launch {
            authViewModel.registerFlow.test {
                assertThat(awaitItem()).isInstanceOf(Resource::class.java)
                assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)

                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `if register fail respond with failure flow`() = runTest {
        authRepository.setShouldReturnError(true)
        authViewModel.register("Name", "valid@email.com", "1234")

        launch {
            authViewModel.registerFlow.test {
                assertThat(awaitItem()).isInstanceOf(Resource::class.java)
                assertThat(awaitItem()).isInstanceOf(Resource.Failure::class.java)

                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `when call finishLogin successfully set login flow to success`() = runTest {
       authViewModel.loginWithGoogle(mockk())

        launch {
            authViewModel.loginFlow.test {
                val item = awaitItem()
                assertThat(item).isInstanceOf(Resource.Success::class.java)

                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `when call finishLogin and return error set login flow to failure`() = runTest {
        authRepository.setShouldReturnError(true)
        authViewModel.loginWithGoogle(mockk())

        launch {
            authViewModel.loginFlow.test {
                val item = awaitItem()
                assertThat(item).isInstanceOf(Resource.Failure::class.java)

                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `when logout called register and login flow must be null`() = runTest {
        authViewModel.logout()

        launch {
            authViewModel.loginFlow.test {
                assertThat(awaitItem()).isNull()
                cancelAndConsumeRemainingEvents()
            }
            authViewModel.registerFlow.test {
                assertThat(awaitItem()).isNull()
                cancelAndConsumeRemainingEvents()
            }
        }
    }

}