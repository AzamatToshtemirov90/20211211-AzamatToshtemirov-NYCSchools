package com.azamat.nycschoolforartech.util

import com.azamat.nycschoolforartech.base.BaseApiResult
import com.azamat.nycschoolforartech.model.remote.response.ErrorResponseBody
import com.google.gson.Gson
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class ResponseHandler {
    fun <T> handleSuccess(data: T): BaseApiResult<T> {
        return BaseApiResult.success(data)
    }

    fun <T> handleException(e: Exception): BaseApiResult<T> {

        return when (e) {
            is HttpException -> {
                val errorResponse = Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponseBody::class.java)
                BaseApiResult.error(getErrorMessage(e.code(), errorResponse.message), null)
            }
            is SocketTimeoutException -> BaseApiResult.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
//            is IOException -> BaseApiResult.error("No internet connection", null)
            else -> BaseApiResult.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int, message: String? = null): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> {
                "Error code: $code\nError message: $message"
            }
        }
    }
}
