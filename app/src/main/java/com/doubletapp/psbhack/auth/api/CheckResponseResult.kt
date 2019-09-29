package com.doubletapp.psbhack.auth.api

import com.google.gson.annotations.SerializedName

data class CheckResponseResult(

        @SerializedName("result")
        val result: Boolean,

        @SerializedName("image_url")
        val imageUrl: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("subtitile")
        val subtitile: String

)