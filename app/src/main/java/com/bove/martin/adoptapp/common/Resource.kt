package com.bove.martin.adoptapp.common

/**
 * Created by Martín Bove on 25/11/2022.
 * E-mail: mbove77@gmail.com
 */
sealed class Resource<out R> {
    data class Success<out R>(val result: R): Resource<R>()
    data class Failure(val exception: Exception): Resource<Nothing>()
    object  Loading: Resource<Nothing>()
    object  StartGoogleLogin: Resource<Nothing>()
}