package com.turk.common.extension


import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.turk.common.error.ErrorEntity
import org.koin.androidx.viewmodel.ViewModelParameter
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent

fun <T : Any, L : LiveData<T>> AppCompatActivity.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<ErrorEntity>> AppCompatActivity.failure(
    liveData: L,
    body: (ErrorEntity?) -> Unit
) =
    liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<T>> Fragment.removeObserver(liveData: L) =
    liveData.removeObservers(this)

@Suppress("unused")
fun <L : LiveData<ErrorEntity>> Fragment.fault(liveData: L, body: (ErrorEntity?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))

inline fun <reified VM : ViewModel> Fragment.sharedGraphViewModel(
    @IdRes navGraphId: Int,
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy {
    val store = findNavController().getViewModelStoreOwner(navGraphId).viewModelStore
    KoinJavaComponent.getKoin()
        .getViewModel(ViewModelParameter(VM::class, qualifier, parameters, null, store, null))
}