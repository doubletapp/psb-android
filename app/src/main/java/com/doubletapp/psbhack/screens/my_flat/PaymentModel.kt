package com.doubletapp.psbhack.screens.my_flat

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentModel(

        val date: String,

        val cost: String

) : Parcelable