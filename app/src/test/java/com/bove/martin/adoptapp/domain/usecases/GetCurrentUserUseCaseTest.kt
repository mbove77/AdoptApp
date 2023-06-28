package com.bove.martin.adoptapp.domain.usecases

import com.bove.martin.adoptapp.data.AuthRepository
import com.google.firebase.auth.FirebaseUser
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Martín Bove on 20/06/2023.
 * E-mail: mbove77@gmail.com
 */
class GetCurrentUserUseCaseTest {
    private lateinit var getCurrentUserUseCase: GetCurrentUserUseCase
    private lateinit var authRepository: AuthRepository

    @Before
    fun setUp() {
        authRepository = mockk()
        getCurrentUserUseCase = GetCurrentUserUseCase(authRepository)
    }

    @Test
    fun `when authRepository returns non-null currentUser, invoke() should return the currentUser`() {
        // given
        val currentUser = mockk<FirebaseUser>()
        every { authRepository.currentUser } returns currentUser

        // when
        val result = getCurrentUserUseCase()

        // then
        assertEquals(currentUser, result)
    }

    @Test
    fun `when authRepository returns null currentUser, invoke() should return null`() {
        // given
        every { authRepository.currentUser } returns null

        // when
        val result = getCurrentUserUseCase()

        // then
        assertEquals(null, result)
    }
}