package com.doubletapp.psbhack.vk.api.users

import com.google.gson.annotations.SerializedName

data class LastSeen(

        @SerializedName("platform")
        val platform: Int,

        @SerializedName("time")
        val time: Int

)