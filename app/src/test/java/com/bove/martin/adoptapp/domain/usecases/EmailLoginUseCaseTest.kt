package com.bove.martin.adoptapp.domain.usecases

import com.bove.martin.adoptapp.data.AuthRepository
import com.bove.martin.adoptapp.data.Resource
import com.google.firebase.auth.FirebaseUser
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
 * Created by Martín Bove on 11/06/2023.
 * E-mail: mbove77@gmail.com
 */

class EmailLoginUseCaseTest {
    private lateinit var emailLoginUseCase: EmailLoginUseCase
    private lateinit var authRepository: AuthRepository

    @Before
    fun setUp() {
        authRepository = mockk()
        emailLoginUseCase = EmailLoginUseCase(authRepository)
    }
    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `login should return success`() = runBlocking {
        // given
        val email = "john.doe@example.com"
        val password = "password"
        val firebaseUser = mockk<FirebaseUser>()
        coEvery { authRepository.login(email, password) } returns Resource.Success(firebaseUser)

        // when
        val result = emailLoginUseCase(email, password)

        // then
        assert(result is Resource.Success)
        coVerify { authRepository.login(email, password) }
    }

    @Test
    fun `login should return error`() = runBlocking {
        // given
        val email = "john.doe@example.com"
        val password = "password"
        val errorMessage = "Login failed"
        coEvery { authRepository.login(email, password) } returns Resource.Failure(Exception(errorMessage))

        // when
        val result = emailLoginUseCase(email, password)

        // then
        assert(result is Resource.Failure)
        assertEquals(errorMessage, (result as Resource.Failure).exception.message)
        coVerify { authRepository.login(email, password) }
    }
}