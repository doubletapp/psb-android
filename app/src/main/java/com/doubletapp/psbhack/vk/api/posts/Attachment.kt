package com.doubletapp.psbhack.vk.api.posts

import com.google.gson.annotations.SerializedName

data class Attachment(

        @SerializedName("photo")
        val photo: Photo,

        @SerializedName("type")
        val type: String

)