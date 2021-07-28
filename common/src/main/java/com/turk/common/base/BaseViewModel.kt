package com.turk.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turk.common.error.ErrorEntity
import com.turk.common.livedata.SingleLiveEventMutableLiveData
import com.turk.common.mvi.*
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/**
 * This class is base class for all the view models to use this class you have to provide below properties
 * [INTENT] Intention of view model to be used
 * [ACTION] Action taken on the basis  of intent
 * [STATE] what are the possible state of the views using this view model
 */
abstract class BaseViewModel<STATE : ViewState, ACTION : ViewAction>(initialState:STATE, private val reducer: Reducer<STATE, ACTION>) :
    ViewModel(){


    //region Live Data
    /**
     * Handle the errors of the view model
     */
    var errorEntity: SingleLiveEventMutableLiveData<ErrorEntity> = SingleLiveEventMutableLiveData()

    //endregion

    //region Failure Handling
    protected fun handleFailure(errorEntity: ErrorEntity) {
        this.errorEntity.value = errorEntity
    }
    //endregion

    private val store=Store(initialState = initialState,reducer = reducer)

     val _viewState: LiveData<STATE> = store.state


      fun dispatch(action: ACTION) {
          viewModelScope.launch {

              processAction(action)
          }
    }

    open fun processAction(action: ACTION){

        store.dispatch(action)
    }





}