package com.doubletapp.psbhack.base

import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alex66rus.basearchkotlin.base.SingleLiveEvent
import com.alex66rus.basearchkotlin.presentation.base.event.ViewEvent
import com.alex66rus.basearchkotlin.presentation.base.view_model.EventViewModel
import com.doubletapp.psbhack.extensions.toSingle
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(), EventViewModel, LifecycleObserver {

    protected val disposables = CompositeDisposable()

    val events: LiveData<Array<out ViewEvent>?> = SingleLiveEvent()

    @MainThread
    override fun postViewEvents(vararg viewEvents: ViewEvent) {

        events.toSingle().value = viewEvents
    }

    @CallSuper
    public override fun onCleared() = disposables.clear()
}