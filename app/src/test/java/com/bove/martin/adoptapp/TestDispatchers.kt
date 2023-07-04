package com.bove.martin.adoptapp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Martín Bove on 04/07/2023.
 * E-mail: mbove77@gmail.com
 */
class TestDispatchers: DispatchersProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val io: CoroutineDispatcher
        get() =  Dispatchers.Unconfined
    override val default: CoroutineDispatcher
        get() =  Dispatchers.Unconfined
}