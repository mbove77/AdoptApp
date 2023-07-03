package com.bove.martin.adoptapp.domain.usecases

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Martín Bove on 03/07/2023.
 * E-mail: mbove77@gmail.com
 */
class LogOutUseCaseTest {
    private lateinit var logOutUseCase: LogOutUseCase

    @Before
    fun setUp() {
        logOutUseCase = mockk()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `invoke should call logout on authRepository`() {
        every { logOutUseCase.invoke() } just runs

        logOutUseCase.invoke()

        verify(exactly = 1) { logOutUseCase.invoke() }
    }
}