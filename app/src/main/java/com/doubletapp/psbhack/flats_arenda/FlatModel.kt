package com.doubletapp.psbhack.flats_arenda

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlatModel(

        val status: String,

        val city: String,

        val address: String,

        val cost: String,

        val date: String,

        val arendatorName: String,

        val arandatorDate: String,

        val arendatorPassport: String,

        val arendatorPhotoPaths: List<String> = emptyList(),

        val arendatorDocumentPath: List<String> = emptyList(),

        val nextPaymentDate: String? = null,

        val nextArendatorsEventDate: String? = null

) : Parcelable