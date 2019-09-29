package com.doubletapp.psbhack.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alex66rus.basearchkotlin.base.SingleLiveEvent
import com.doubletapp.psbhack.auth.api.BackRepo
import com.doubletapp.psbhack.base.BaseViewModel
import com.doubletapp.psbhack.extensions.toMutable
import com.doubletapp.psbhack.extensions.toSingle
import com.vk.api.sdk.auth.VKAccessToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign

class AuthViewModel : BaseViewModel() {

    private val vkTokenPreference by lazy { VkAuthTokenPreference() }

    private val authPreference by lazy { AuthTokenPreference() }

    val error: LiveData<Throwable> = SingleLiveEvent()

    val loading: LiveData<Boolean> = MutableLiveData()

    val showVkDialog: LiveData<Unit?> = SingleLiveEvent()

    val showNextScreen: LiveData<String?> = SingleLiveEvent()

    fun onAuthButtonClick(auth: String) = when (vkTokenPreference.hasToken(auth)) {
        true -> showNextScreen.toSingle().call()
        else -> {
            authPreference.putToken(auth)
            showVkDialog.toSingle().call()
        }
    }

    fun onVkAuthDecline() = showNextScreen.toSingle().call()

    fun onVkAuthLogin(auth: String, token: VKAccessToken) {
        vkTokenPreference.putToken(auth, token)
        disposables += BackRepo
                .postToken(token)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.toMutable().value = true }
                .doAfterTerminate { loading.toMutable().value = false }
                .subscribe({ result ->
                    authPreference.putRequestId(result.requestId)
                    showNextScreen.toSingle().value = result.requestId
                }, { throwable ->
                    error.toMutable().value = throwable
                })
    }
}