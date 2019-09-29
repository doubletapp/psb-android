package com.doubletapp.psbhack.vk.api.posts

import com.google.gson.annotations.SerializedName

data class Reposts(

        @SerializedName("count")
        val count: Int,

        @SerializedName("user_reposted")
        val userReposted: Int

)