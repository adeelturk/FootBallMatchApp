package com.turk.common.mvi

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow


/**
 * This class should implemented by all the Viewmodels, sole purpose of this is to provide handles
 * to ViewModels to play with MVI(Model-View-Intent)
 */
interface IModel< S:ViewState,in A:ViewAction> {

    /**
     * This holdsthe state of the viewModel
     */
     val store:Store<S,A>



    /**
     * This method is used by fragment to dispatch the initial intent of the view model
     */
    fun dispatch(action: A)
}