package com.turk.common.base

import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


/**
 * Provide the Implementation of the Async calls to implemented classes
 * [Type] is covariant which is defined as the type of response required by caller function
 * [Params] is contravariant  which defines the type of parameters which is passed by the function as input
 */
abstract class BaseUseCase<out Type, in Params> {

    /**
     * this should be called from Coroutine Context and implemented by all Use cases
     * */
    abstract suspend fun run(param: Params): Flow<Either<ErrorEntity, Type>>


    fun fetchData(
        viewModelScope: CoroutineScope,
        params: Params,
        onResult: (Flow<Either<ErrorEntity, Type>>) -> Unit
    ) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO)
                .launch {
                    val result = run(params)
                    onResult(result)

                }
        }
    }

    /**
     * This is an operator function which is invoke from the viewmodel and
     * it collects data from flow and pass the concrete data to viewModel
     */
    operator fun invoke(
        viewModelScope: CoroutineScope,
        params: Params,
        onResult: (Either<ErrorEntity, Type>) -> Unit
    ) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {

                run(params).collectLatest {
                    withContext(Dispatchers.Main) {
                        onResult(it)
                    }
                }
            }

        }


    }


    /**
     * When our Network Call don't need any parameter pass this class
     */
    class None
}

// use this way if you need the result of the operation in a concurrent way
//viewModelScope.launch {
//    val async = CoroutineScope(Dispatchers.IO)
//        .async {
//            run(params)
//        }
//
//    onResult(async.await())
//}`