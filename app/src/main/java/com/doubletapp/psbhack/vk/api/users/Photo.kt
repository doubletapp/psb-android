package com.doubletapp.psbhack.vk.api.users

import com.doubletapp.psbhack.vk.api.users.Size
import com.google.gson.annotations.SerializedName

data class Photo(

    @SerializedName("album_id")
        val albumId: Int,

    @SerializedName("date")
        val date: Int,

    @SerializedName("id")
        val id: Int,

    @SerializedName("owner_id")
        val ownerId: Int,

    @SerializedName("post_id")
        val postId: Int,

    @SerializedName("sizes")
        val sizes: List<Size>,

    @SerializedName("text")
        val text: String

)