package com.bove.martin.adoptapp.domain.usecases

import com.bove.martin.adoptapp.data.FakeAuthRepository
import com.bove.martin.adoptapp.data.Resource
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.FirebaseUser
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Martín Bove on 11/06/2023.
 * E-mail: mbove77@gmail.com
 */

class EmailLoginUseCaseTest {
    private lateinit var emailLoginUseCase: EmailLoginUseCase
    private lateinit var authRepository: FakeAuthRepository
    private lateinit var firebaseUser: FirebaseUser

    @Before
    fun setUp() {
        firebaseUser = mockk(relaxed = true)
        authRepository = FakeAuthRepository(firebaseUser)
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

        // when
        val result = emailLoginUseCase(email, password)

        // then
        assertThat(result).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `login should return error`() = runBlocking {
        // given
        val email = "john.doe@example.com"
        val password = "password"
        authRepository.setShouldReturnError(true)

        // when
        val result = emailLoginUseCase(email, password)

        // then
        assertThat(result).isInstanceOf(Resource.Failure::class.java)
    }
}