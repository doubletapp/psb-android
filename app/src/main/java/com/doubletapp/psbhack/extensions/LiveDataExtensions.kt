package com.doubletapp.psbhack.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.alex66rus.basearchkotlin.base.SingleLiveEvent
import java.lang.ClassCastException

inline fun <reified T> LiveData<*>.convert(): T {

    return this as? T ?: throw ClassCastException("${this::class} is not ${T::class}")
}

fun <T> LiveData<T>.toMutable() = convert<MutableLiveData<T>>()

fun <T> LiveData<T>.toSingle() = convert<SingleLiveEvent<T>>()

fun <I, O> LiveData<I>.map(mapper: (I) -> O): LiveData<O> {

    return Transformations.map(this, androidx.arch.core.util.Function(mapper::invoke))
}