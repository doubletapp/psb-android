package com.doubletapp.psbhack.vk.api.users

import com.google.gson.annotations.SerializedName

data class Rect(

        @SerializedName("x")
        val x: Double,

        @SerializedName("x2")
        val x2: Double,

        @SerializedName("y")
        val y: Int,

        @SerializedName("y2")
        val y2: Int

)