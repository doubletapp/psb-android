package com.doubletapp.psbhack.flats_arenda

import android.content.Context
import androidx.core.content.edit
import com.doubletapp.psbhack.PsbApp
import com.google.gson.Gson

class FlatsRepo {

    private val store by lazy { PsbApp.context!!.getSharedPreferences(KEY_STORE, Context.MODE_PRIVATE) }

    private val gson by lazy { Gson() }

    companion object {

        private const val KEY_STORE = "FlatsRepo"

        private const val KEY_FLATS = "key_flats"
    }

    fun putFlats(flats: List<FlatModel>) {
        store.edit {
            clear()
            putString(KEY_FLATS, gson.toJson(flats))
        }
    }

    fun getFlats(): List<FlatModel> = store.getString(KEY_FLATS, null)?.let { json ->
        gson.fromJson(json, Array<FlatModel>::class.java).toList()
    } ?: emptyList()

    fun putFlat(flat: FlatModel) {
        val flats = getFlats().toMutableList()
        flats.add(flat)
        putFlats(flats)
    }

    fun removeFlat(flat: FlatModel) {
        val flats = getFlats().toMutableList()
        flats.remove(flat)
        putFlats(flats)
    }

    fun changeFlat(oldFlat: FlatModel?, newFlat: FlatModel) {
        if (oldFlat == null) return
        val flats = getFlats().toMutableList()
        val index = flats.indexOf(oldFlat)
        if (index!=-1) flats[index] = newFlat
        putFlats(flats)
    }
}