package com.doubletapp.psbhack.main_npd

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NpdProfile(

        val sumLastMonth: String = "900",

        val nameLastMonth: String = "Сентябрь",

        val dolg: String = "0",

        var isArendaConnected: Boolean = false,

        var isClientConnected: Boolean = false

) : Parcelable