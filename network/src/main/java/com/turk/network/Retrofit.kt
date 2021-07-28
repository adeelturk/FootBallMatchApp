package com.turk.network

import android.util.Log
import com.google.gson.Gson
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Call


/**
 * Takes in a transform lambda to return a modified version of the responses
 */

var generalErrorImplementation = GeneralErrorImplementation()

@Suppress("unused")
fun <T, R> Call<T>.requestBlocking(transform: (T) -> R): Either<ErrorEntity, R> {
    return try {
        val response = execute()
        response.errorBody()
        when (response.isSuccessful) {
            true -> Either.Right(transform(response.body()!!))

            false -> Either.Left(generalErrorImplementation.getHttpErrors(response))
        }
    } catch (exception: Throwable) {

        Either.Left(generalErrorImplementation.getError(exception))

    }

}

@Suppress("unused")
 fun <T, R> Call<T>.requestBlockingFlow(transform: (T) -> R): Flow<Either<ErrorEntity, R>> {

    return flow {

            try {

                val response = execute()
                response.errorBody()
                when (response.isSuccessful) {
                    true -> {
                       
                        emit(Either.Right(transform(response.body()!!)))

                    }

                    false -> {
                       
                        emit(Either.Left(generalErrorImplementation.getHttpErrors(response)))

                    }
                }
            } catch (exception: Throwable) {
               
                emit(Either.Left(generalErrorImplementation.getError(exception)))

            }

    }

}












