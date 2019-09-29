package com.doubletapp.psbhack.vk.api.users

import com.google.gson.annotations.SerializedName

data class Country(

        @SerializedName("id")
        val id: Int,

        @SerializedName("title")
        val title: String

)