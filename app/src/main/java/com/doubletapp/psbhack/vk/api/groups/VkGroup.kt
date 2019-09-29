package com.doubletapp.psbhack.vk.api.groups

import com.google.gson.annotations.SerializedName

data class VkGroup(

        @SerializedName("id")
        val id: Int,

        @SerializedName("is_admin")
        val isAdmin: Int,

        @SerializedName("is_advertiser")
        val isAdvertiser: Int,

        @SerializedName("is_closed")
        val isClosed: Int,

        @SerializedName("is_member")
        val isMember: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("photo_100")
        val photo100: String,

        @SerializedName("photo_200")
        val photo200: String,

        @SerializedName("photo_50")
        val photo50: String,

        @SerializedName("screen_name")
        val screenName: String,

        @SerializedName("type")
        val type: String

) {

    companion object {

        fun allFields() = "city, country, place, description, wiki_page, members_count, counters, start_date, finish_date, can_post, can_see_all_posts, activity, status, contacts, links, fixed_post, verified, site, can_create_topic"
    }
}