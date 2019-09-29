package com.doubletapp.psbhack.vk.api.notes

import com.google.gson.annotations.SerializedName

data class VkNote(

        @SerializedName("comments")
        val comments: Int,

        @SerializedName("date")
        val date: Int,

        @SerializedName("id")
        val id: Int,

        @SerializedName("owner_id")
        val ownerId: Int,

        @SerializedName("text")
        val text: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("view_url")
        val viewUrl: String

)