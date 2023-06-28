package com.bove.martin.adoptapp.domain.usecases

import com.bove.martin.adoptapp.data.AuthRepository
import com.bove.martin.adoptapp.data.Resource
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


/**
 * Created by Martín Bove on 20/06/2023.
 * E-mail: mbove77@gmail.com
 */

class FinishGoogleLoginUseCaseTest {
    private lateinit var finishGoogleLoginUseCase: FinishGoogleLoginUseCase
    private lateinit var authRepository: AuthRepository

    @Before
    fun setUp() {
        authRepository = mockk()
        finishGoogleLoginUseCase = FinishGoogleLoginUseCase(authRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }


    @Test
    fun `google login should return success`() = runBlocking {
        // given
        val googleSignInAccount = mockk<GoogleSignInAccount>(relaxed = true)
        val task = mockk<Task<GoogleSignInAccount>>(relaxed = true)
        coEvery { authRepository.googleLogin(task) } returns Resource.Success(mockk())
        coEvery { task.getResult(ApiException::class.java) } returns googleSignInAccount

        // when
        val result = finishGoogleLoginUseCase(task)

        // then
        assert(result is Resource.Success)
        coVerify { authRepository.googleLogin(task) }
    }


    @Test
    fun `google login should return error`() = runBlocking {
        // given
        val task = mockk<Task<GoogleSignInAccount>>(relaxed = true)
        val errorMessage = "Google login failed"
        coEvery { authRepository.googleLogin(task) } returns Resource.Failure(Exception(errorMessage))
        coEvery { task.getResult(ApiException::class.java) } throws ApiException(mockk(relaxed = true))

        // when
        val result = finishGoogleLoginUseCase(task)

        // then
        assert(result is Resource.Failure)
        assertEquals(errorMessage, (result as Resource.Failure).exception.message)
        coVerify { authRepository.googleLogin(task) }
    }

}