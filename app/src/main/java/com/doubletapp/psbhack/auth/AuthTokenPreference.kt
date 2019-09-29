package com.doubletapp.psbhack.auth

import android.content.Context
import androidx.core.content.edit
import com.doubletapp.psbhack.PsbApp

class AuthTokenPreference {

    private val store by lazy { PsbApp.context!!.getSharedPreferences(KEY_STORE, Context.MODE_PRIVATE) }

    companion object {

        private const val KEY_STORE = "AuthTokenPreference"

        private const val KEY_TOKEN = "key_token"

        private const val KEY_REQUEST_ID = "key_request_id"
    }

    fun putRequestId(requestId: String) = store.edit {
        putString(KEY_REQUEST_ID, requestId)
    }

    fun getRequestId(): String? = store.getString(KEY_REQUEST_ID, null)

    fun putToken(token: String) = store.edit {
        putString(KEY_TOKEN, token)
    }

    fun getToken(): String? = store.getString(KEY_TOKEN, null)

    fun hasToken(): Boolean = !getToken().isNullOrBlank()

    fun clear() = store.edit {
        clear()
    }
}