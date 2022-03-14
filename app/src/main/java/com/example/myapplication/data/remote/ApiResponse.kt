package com.example.myapplication.data.remote

import retrofit2.Response

sealed class ApiResponse<T> {
    data class ResponseSuccess<T>(val response: Response<T>) : ApiResponse<T>()
    data class ResponseFailure<T>(val message: String) : ApiResponse<T>()

    companion object {
        suspend fun <T> ApiResponse<T>.onSuccessSuspend(callback: suspend ResponseSuccess<T>.() -> Unit): ApiResponse<T> {
            if (this is ResponseSuccess) callback()
            return this
        }

        suspend fun <T> ApiResponse<T>.onErrorSuspend(callback: suspend ResponseFailure<T>.() -> Unit): ApiResponse<T> {
            if (this is ResponseFailure) callback()
            return this
        }

        fun <T> success(data : Response<T>): ApiResponse<T> = ResponseSuccess(data)

        fun <T> error(message: String): ApiResponse<T> = ResponseFailure(message)

        fun <T> checkResult(successCodeRange: IntRange = 200..299, response: Response<T>): ApiResponse<T> {
            return if (response.raw().code in successCodeRange) {
                ResponseSuccess(response)
            } else {
                ResponseFailure("Error")
            }
        }
    }
}