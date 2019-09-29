package com.doubletapp.psbhack

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication

class PsbApp : MultiDexApplication() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()

        context = this
    }
}