package com.doubletapp.psbhack.vk.api.posts

import com.google.gson.annotations.SerializedName

data class Photo(

        @SerializedName("access_key")
        val accessKey: String,

        @SerializedName("album_id")
        val albumId: Int,

        @SerializedName("date")
        val date: Int,

        @SerializedName("height")
        val height: Int,

        @SerializedName("id")
        val id: Int,

        @SerializedName("owner_id")
        val ownerId: Int,

        @SerializedName("photo_1280")
        val photo1280: String,

        @SerializedName("photo_130")
        val photo130: String,

        @SerializedName("photo_604")
        val photo604: String,

        @SerializedName("photo_75")
        val photo75: String,

        @SerializedName("photo_807")
        val photo807: String,

        @SerializedName("text")
        val text: String,

        @SerializedName("user_id")
        val userId: Int,

        @SerializedName("width")
        val width: Int

)