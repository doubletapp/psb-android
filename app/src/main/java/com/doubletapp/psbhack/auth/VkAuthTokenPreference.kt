package com.doubletapp.psbhack.auth

import android.content.Context
import androidx.core.content.edit
import com.doubletapp.psbhack.PsbApp
import com.google.gson.Gson
import com.vk.api.sdk.auth.VKAccessToken

class VkAuthTokenPreference {

    private val store by lazy { PsbApp.context!!.getSharedPreferences(KEY_STORE, Context.MODE_PRIVATE) }

    private val gson by lazy { Gson() }

    companion object {

        private const val KEY_STORE = "VkAuthTokenPreference"
    }

    fun putToken(key: String, token: VKAccessToken) {
        store.edit {
            clear()
            putString(key, gson.toJson(token))
        }
    }

    fun getToken(key: String): VKAccessToken? = store.getString(key, null)?.let { token ->
        gson.fromJson(token, VKAccessToken::class.java)
    }

    fun hasToken(key: String): Boolean = !store.getString(key, null).isNullOrBlank()

    fun clearToken() {
        store.edit {
            clear()
        }
    }
}