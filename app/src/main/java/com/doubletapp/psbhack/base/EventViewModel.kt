package com.alex66rus.basearchkotlin.presentation.base.view_model

import androidx.annotation.MainThread
import com.alex66rus.basearchkotlin.presentation.base.event.ViewEvent

interface EventViewModel {

    @MainThread
    fun postViewEvents(vararg viewEvents: ViewEvent)
}