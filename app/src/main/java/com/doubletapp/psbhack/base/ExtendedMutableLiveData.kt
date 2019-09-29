package com.alex66rus.basearchkotlin.base

import androidx.lifecycle.MutableLiveData

class ExtendedMutableLiveData<T>(

        initialValue: T? = null,
        onChangeAction: ((T) -> Unit)? = null

) : MutableLiveData<T>() {

    private var previousValue: T? = null

    init {
        value = initialValue

        onChangeAction?.let {
            observeForever { value ->
                if (previousValue != value) {
                    previousValue = value
                    value?.let(onChangeAction::invoke)
                }
            }
        }
    }
}