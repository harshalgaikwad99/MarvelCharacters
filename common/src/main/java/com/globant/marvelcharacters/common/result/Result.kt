package com.globant.marvelcharacters.common.result

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    val isSuccess get() = this is Success<R>
    val getSuccessData get() = this as Success<R>
    val getError get() = this as Error
}