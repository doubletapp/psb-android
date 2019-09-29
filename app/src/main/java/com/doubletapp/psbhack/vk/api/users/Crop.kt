package com.doubletapp.psbhack.vk.api.users

import com.google.gson.annotations.SerializedName

data class Crop(

        @SerializedName("x")
        val x: Double,

        @SerializedName("x2")
        val x2: Double,

        @SerializedName("y")
        val y: Double,

        @SerializedName("y2")
        val y2: Double

)