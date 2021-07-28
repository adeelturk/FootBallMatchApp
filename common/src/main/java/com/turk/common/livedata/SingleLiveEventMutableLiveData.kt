package com.turk.common.livedata

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.turk.common.extension.tag
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEventMutableLiveData<T> : MutableLiveData<T>() {
    private val pending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {


        super.observe(owner, { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })

        if (hasActiveObservers()) {
            Log.w(tag(), "single observer is notified of change ")
        }
    }

    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    override fun postValue(value: T) {
        super.postValue(value)
    }

}
