package com.doubletapp.psbhack.main_npd

import android.content.Context
import androidx.core.content.edit
import com.doubletapp.psbhack.PsbApp

class RegTokenPreference {
    private val store by lazy { PsbApp.context!!.getSharedPreferences(KEY_STORE, Context.MODE_PRIVATE) }

    companion object {

        private const val KEY_STORE = "RegPreference"

        private const val KEY_TOKEN = "key_token"

    }

    fun setRegFinished() = store.edit {
        putBoolean(KEY_TOKEN, true)
    }

    fun isRegFinished(): Boolean = store.getBoolean(KEY_TOKEN, false)

    fun clear() = store.edit {
        clear()
    }
}