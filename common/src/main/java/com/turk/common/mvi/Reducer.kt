package com.turk.common.mvi

interface Reducer<S:ViewState,in A: ViewAction> {

    fun reduce (currentState:S,action:A):S
}