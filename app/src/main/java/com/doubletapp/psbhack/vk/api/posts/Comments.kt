package com.doubletapp.psbhack.vk.api.posts

import com.google.gson.annotations.SerializedName

data class Comments(

        @SerializedName("can_post")
        val canPost: Int,

        @SerializedName("count")
        val count: Int

)