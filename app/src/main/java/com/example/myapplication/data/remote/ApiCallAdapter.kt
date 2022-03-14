package com.example.myapplication.data.remote

import android.util.Log
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class ApiCallAdapter(val type: Type): CallAdapter<Type, Call<ApiResponse<Type>>> {

    internal class ApiResponseCall(private val call: Call<Type>) : Call<ApiResponse<Type>> {
        override fun enqueue(callback: Callback<ApiResponse<Type>>) {
            call.enqueue(object : Callback<Type> {

                override fun onResponse(call: Call<Type>, response: Response<Type>) {
                    val apiResponse = ApiResponse.checkResult(response = response)
                    callback.onResponse(this@ApiResponseCall, Response.success(apiResponse))
                }

                override fun onFailure(call: Call<Type>, t: Throwable) {
                    Log.d("TAG", "onFailure: >>>>>>>>>>>>")
                    callback.onResponse(this@ApiResponseCall, Response.success(ApiResponse.error("Error")))
                }
            })
        }

        override fun cancel() = call.cancel()
        override fun request(): Request = call.request()
        override fun isExecuted() = call.isExecuted
        override fun isCanceled() = call.isCanceled
        override fun execute(): Response<ApiResponse<Type>> =
            throw UnsupportedOperationException("ApiResponseCallAdapter doesn't support execute")

        override fun timeout(): Timeout = Timeout.NONE
        override fun clone(): Call<ApiResponse<Type>> = this
    }

    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<ApiResponse<Type>> {
        return ApiResponseCall(call)
    }
}