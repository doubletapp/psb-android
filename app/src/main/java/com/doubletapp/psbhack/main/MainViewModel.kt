package com.doubletapp.psbhack.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alex66rus.basearchkotlin.base.ExtendedMutableLiveData
import com.alex66rus.basearchkotlin.base.SingleLiveEvent
import com.doubletapp.psbhack.auth.AuthTokenPreference
import com.doubletapp.psbhack.auth.VkAuthTokenPreference
import com.doubletapp.psbhack.auth.api.BackRepo
import com.doubletapp.psbhack.auth.api.CheckResponseResult
import com.doubletapp.psbhack.base.BaseViewModel
import com.doubletapp.psbhack.extensions.toMutable
import com.doubletapp.psbhack.extensions.toSingle
import com.doubletapp.psbhack.main_npd.RegTokenPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign

class MainViewModel : BaseViewModel() {

    private val vkTokenPreference by lazy { VkAuthTokenPreference() }

    private val authPreference by lazy { AuthTokenPreference() }

    private val regPreference by lazy { RegTokenPreference() }

    private val requestId = ExtendedMutableLiveData<String> { checkResponse(it) }

    val npdInfo: LiveData<CheckResponseResult> = MutableLiveData()

    val loggedOut: LiveData<Unit> = SingleLiveEvent()

    fun setRequestId(requestId: String?) {
        this.requestId.value = requestId
    }

    private fun checkResponse(requestId: String?) {
        requestId ?: return
        disposables += BackRepo
                .checkResponse(requestId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    npdInfo.toMutable().value = result
                }, { throwable ->

                })
    }

    fun logout() {
        regPreference.clear()
        authPreference.clear()
        vkTokenPreference.clearToken()
        loggedOut.toSingle().call()
    }
}