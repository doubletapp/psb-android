package com.doubletapp.psbhack.vk.api.goods

import com.google.gson.annotations.SerializedName

data class Currency(

        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String

)