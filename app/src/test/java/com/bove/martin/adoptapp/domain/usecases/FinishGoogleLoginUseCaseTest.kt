package com.bove.martin.adoptapp.domain.usecases

import com.bove.martin.adoptapp.common.Resource
import com.bove.martin.adoptapp.data.FakeAuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.FirebaseUser
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


/**
 * Created by Martín Bove on 20/06/2023.
 * E-mail: mbove77@gmail.com
 */

class FinishGoogleLoginUseCaseTest {
    private lateinit var finishGoogleLoginUseCase: FinishGoogleLoginUseCase
    private lateinit var authRepository: FakeAuthRepository
    private lateinit var firebaseUser: FirebaseUser

    @Before
    fun setUp() {
        firebaseUser = mockk(relaxed = true)
        authRepository = FakeAuthRepository(firebaseUser)
        finishGoogleLoginUseCase = FinishGoogleLoginUseCase(authRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }


    @Test
    fun `google login should return success`() = runBlocking {
        // given
        val task = mockk<Task<GoogleSignInAccount>>(relaxed = true)

        // when
        val result = finishGoogleLoginUseCase(task)

        // then
        assertThat(result).isInstanceOf(Resource.Success::class.java)
    }


    @Test
    fun `google login should return error`() = runBlocking {
        // given
        val task = mockk<Task<GoogleSignInAccount>>(relaxed = true)
        authRepository.setShouldReturnError(true)

        // when
        val result = finishGoogleLoginUseCase(task)

        // then
        assertThat(result).isInstanceOf(Resource.Failure::class.java)
    }

}