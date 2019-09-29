package com.doubletapp.psbhack.vk.api.posts

import com.doubletapp.psbhack.vk.api.posts.*
import com.google.gson.annotations.SerializedName

data class VkPost(

    @SerializedName("attachments")
        val attachments: List<Attachment>,

    @SerializedName("can_pin")
        val canPin: Int,

    @SerializedName("comments")
        val comments: Comments,

    @SerializedName("date")
        val date: Int,

    @SerializedName("from_id")
        val fromId: Int,

    @SerializedName("id")
        val id: Int,

    @SerializedName("likes")
        val likes: Likes,

    @SerializedName("marked_as_ads")
        val markedAsAds: Int,

    @SerializedName("owner_id")
        val ownerId: Int,

    @SerializedName("post_source")
        val postSource: PostSource,

    @SerializedName("post_type")
        val postType: String,

    @SerializedName("reposts")
        val reposts: Reposts,

    @SerializedName("text")
        val text: String,

    @SerializedName("views")
        val views: Views

)