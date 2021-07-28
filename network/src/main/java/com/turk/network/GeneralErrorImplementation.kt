package com.turk.network

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import com.turk.common.error.ErrorEntity
import com.turk.common.error.ErrorHandler
import retrofit2.Response
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.UnknownHostException

class GeneralErrorImplementation : ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {

            is JsonSyntaxException -> ErrorEntity.JsonSyntaxException
            is IllegalStateException -> ErrorEntity.IllegalStateException
            is ConnectException,
            is UnknownHostException,
            -> ErrorEntity.NetworkConnection
            is MalformedJsonException -> ErrorEntity.MalFormedJson
            is SecurityException -> ErrorEntity.AndroidError
            else -> ErrorEntity.ServerError
        }
    }

    override fun <T> getHttpErrors(errorResponse: Response<T>): ErrorEntity {
        return when (errorResponse.code()) {
            HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorEntity.AuthError
            HttpURLConnection.HTTP_BAD_REQUEST -> ErrorEntity.BadRequest
            HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
            HttpURLConnection.HTTP_UNSUPPORTED_TYPE -> ErrorEntity.UnSupportedMediaType
            HttpURLConnection.HTTP_FORBIDDEN -> {
                ErrorEntity.ApiRateLimitExceeded("Api Rate Limit Exceeded")
            }

            HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                ErrorEntity.ServerError
            }


            else -> ErrorEntity.ServerError
        }
    }
}
