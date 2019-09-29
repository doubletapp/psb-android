package com.doubletapp.psbhack.main_npd

import android.content.Context
import androidx.core.content.edit
import com.doubletapp.psbhack.PsbApp
import com.google.gson.Gson

class ProfileRepo {
    private val store by lazy { PsbApp.context!!.getSharedPreferences(KEY_STORE, Context.MODE_PRIVATE) }

    private val gson by lazy { Gson() }

    companion object {

        private const val KEY_STORE = "ProfileRepo"

        private const val KEY_FLATS = "key_profile"
    }

    fun putProfile(profile: NpdProfile) {
        store.edit {
            clear()
            putString(KEY_FLATS, gson.toJson(profile))
        }
    }

    fun getProfile(): NpdProfile = store.getString(KEY_FLATS, null)?.let { json ->
        gson.fromJson(json,NpdProfile::class.java)
    } ?: NpdProfile()
}