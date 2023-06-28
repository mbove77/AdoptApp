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
 * Created by Martín Bove on 27/06/2023.
 * E-mail: mbove77@gmail.com
 */
class RegisterUseCaseTest {
    private lateinit var registerUseCase: RegisterUseCase
    private lateinit var authRepository: AuthRepository

    @Before
    fun setUp() {
        authRepository = mockk()
        registerUseCase = RegisterUseCase(authRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `register should return success`() = runBlocking {
        // given
        val name = "John"
        val email = "john.doe@example.com"
        val password = "password"
        val firebaseUser = mockk<FirebaseUser>()
        coEvery { authRepository.register(name, email, password) } returns Resource.Success(firebaseUser)

        // when
        val result = registerUseCase(name, email, password)

        // then
        assert(result is Resource.Success)
        coVerify { authRepository.register(name, email, password) }
    }

    @Test
    fun `register should return error`() = runBlocking {
        // given
        val name = "John"
        val email = "john.doe@example.com"
        val password = "password"
        val errorMessage = "Registration failed"
        coEvery { authRepository.register(name, email, password) } returns Resource.Failure(Exception(errorMessage))

        // when
        val result = registerUseCase(name, email, password)

        // then
        assert(result is Resource.Failure)
        assertEquals(errorMessage, (result as Resource.Failure).exception.message)
        coVerify { authRepository.register(name, email, password) }
    }
}