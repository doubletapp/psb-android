package com.doubletapp.psbhack.vk.api.goods

import com.google.gson.annotations.SerializedName

data class Likes(

        @SerializedName("count")
        val count: Int,

        @SerializedName("user_likes")
        val userLikes: Int

)