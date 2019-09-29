package com.doubletapp.psbhack.vk.api.goods

import com.doubletapp.psbhack.vk.api.goods.Category
import com.doubletapp.psbhack.vk.api.goods.Likes
import com.doubletapp.psbhack.vk.api.goods.Price
import com.doubletapp.psbhack.vk.api.goods.Reposts
import com.google.gson.annotations.SerializedName

data class VkGood(

    @SerializedName("albums_ids")
        val albumsIds: List<Any>,

    @SerializedName("availability")
        val availability: Int,

    @SerializedName("button_title")
        val buttonTitle: String,

    @SerializedName("can_comment")
        val canComment: Int,

    @SerializedName("can_repost")
        val canRepost: Int,

    @SerializedName("category")
        val category: Category,

    @SerializedName("date")
        val date: Int,

    @SerializedName("description")
        val description: String,

    @SerializedName("external_id")
        val externalId: String,

    @SerializedName("id")
        val id: Int,

    @SerializedName("likes")
        val likes: Likes,

    @SerializedName("owner_id")
        val ownerId: Int,

    @SerializedName("photos")
        val photos: List<Any>,

    @SerializedName("price")
        val price: Price,

    @SerializedName("reposts")
        val reposts: Reposts,

    @SerializedName("thumb_photo")
        val thumbPhoto: String,

    @SerializedName("title")
        val title: String,

    @SerializedName("url")
        val url: String,

    @SerializedName("views_count")
        val viewsCount: Int

)