package com.turk.common.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Store< S:ViewState,in A: ViewAction>(initialState:S, private val reducer:Reducer<S,A>){


   /* private val _state= MutableStateFlow(initialState)
    val state:StateFlow<S> = _state*/

    private val _state = MutableLiveData(initialState)
    val state: LiveData<S>
        get() {
            return _state
        }



    fun dispatch(action:A){

        val currentState=_state.value!!
        val newState=reducer.reduce(currentState,action)
        _state.value=newState
    }
}