package com.doubletapp.psbhack.auth.api

import com.google.gson.annotations.SerializedName

data class PostVkDataBody(

        @SerializedName("vk_user_id")
        val userId: String,

        val data: PostVkDataBodyDataModel
)