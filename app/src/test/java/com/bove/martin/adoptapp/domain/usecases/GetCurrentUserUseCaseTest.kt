package com.bove.martin.adoptapp.domain.usecases

import com.bove.martin.adoptapp.data.FakeAuthRepository
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.FirebaseUser
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

/**
 * Created by Martín Bove on 20/06/2023.
 * E-mail: mbove77@gmail.com
 */
class GetCurrentUserUseCaseTest {
    private lateinit var getCurrentUserUseCase: GetCurrentUserUseCase
    private lateinit var authRepository: FakeAuthRepository
    private lateinit var firebaseUser: FirebaseUser

    @Before
    fun setUp() {
        firebaseUser = mockk()
        authRepository = FakeAuthRepository(firebaseUser)
        getCurrentUserUseCase = GetCurrentUserUseCase(authRepository)
    }

    @Test
    fun `when authRepository returns non-null currentUser, invoke() should return the currentUser`() {
        // given

        // when
        val result = getCurrentUserUseCase()

        // then
        assertThat(result).isEqualTo(firebaseUser)
    }

    @Test
    fun `when authRepository returns null currentUser, invoke() should return null`() {
        // given
        authRepository.setShouldReturnError(true)

        // when
        val result = getCurrentUserUseCase()

        // then
        assertThat(result).isNull()
    }
}