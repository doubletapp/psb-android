package com.doubletapp.psbhack.main_npd

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OperationModel(

        val name: String,

        val date: String,

        val sum: String,

        val withFiz: Boolean

) : Parcelable