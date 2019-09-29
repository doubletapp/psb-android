package com.doubletapp.psbhack.vk.api.users

import com.google.gson.annotations.SerializedName

data class VkUser(

        @SerializedName("about")
        val about: String,

        @SerializedName("activities")
        val activities: String,

        @SerializedName("bdate")
        val bdate: String,

        @SerializedName("blacklisted")
        val blacklisted: Int,

        @SerializedName("blacklisted_by_me")
        val blacklistedByMe: Int,

        @SerializedName("books")
        val books: String,

        @SerializedName("can_access_closed")
        val canAccessClosed: Boolean,

        @SerializedName("can_be_invited_group")
        val canBeInvitedGroup: Boolean,

        @SerializedName("can_post")
        val canPost: Int,

        @SerializedName("can_see_all_posts")
        val canSeeAllPosts: Int,

        @SerializedName("can_see_audio")
        val canSeeAudio: Int,

        @SerializedName("can_send_friend_request")
        val canSendFriendRequest: Int,

        @SerializedName("can_write_private_message")
        val canWritePrivateMessage: Int,

        @SerializedName("career")
        val career: List<Any>,

        @SerializedName("city")
        val city: City,

        @SerializedName("common_count")
        val commonCount: Int,

        @SerializedName("country")
        val country: Country,

        @SerializedName("crop_photo")
        val cropPhoto: CropPhoto,

        @SerializedName("domain")
        val domain: String,

        @SerializedName("faculty")
        val faculty: Int,

        @SerializedName("faculty_name")
        val facultyName: String,

        @SerializedName("first_name")
        val firstName: String,

        @SerializedName("followers_count")
        val followersCount: Int,

        @SerializedName("friend_status")
        val friendStatus: Int,

        @SerializedName("games")
        val games: String,

        @SerializedName("graduation")
        val graduation: Int,

        @SerializedName("has_mobile")
        val hasMobile: Int,

        @SerializedName("has_photo")
        val hasPhoto: Int,

        @SerializedName("home_phone")
        val homePhone: String,

        @SerializedName("home_town")
        val homeTown: String,

        @SerializedName("id")
        val id: Int,

        @SerializedName("interests")
        val interests: String,

        @SerializedName("is_closed")
        val isClosed: Boolean,

        @SerializedName("is_favorite")
        val isFavorite: Int,

        @SerializedName("is_friend")
        val isFriend: Int,

        @SerializedName("is_hidden_from_feed")
        val isHiddenFromFeed: Int,

        @SerializedName("last_name")
        val lastName: String,

        @SerializedName("last_seen")
        val lastSeen: LastSeen,

        @SerializedName("maiden_name")
        val maidenName: String,

        @SerializedName("military")
        val military: List<Any>,

        @SerializedName("mobile_phone")
        val mobilePhone: String,

        @SerializedName("movies")
        val movies: String,

        @SerializedName("music")
        val music: String,

        @SerializedName("nickname")
        val nickname: String,

        @SerializedName("online")
        val online: Int,

        @SerializedName("personal")
        val personal: Personal,

        @SerializedName("photo_100")
        val photo100: String,

        @SerializedName("photo_200")
        val photo200: String,

        @SerializedName("photo_200_orig")
        val photo200Orig: String,

        @SerializedName("photo_400_orig")
        val photo400Orig: String,

        @SerializedName("photo_50")
        val photo50: String,

        @SerializedName("photo_id")
        val photoId: String,

        @SerializedName("photo_max")
        val photoMax: String,

        @SerializedName("photo_max_orig")
        val photoMaxOrig: String,

        @SerializedName("quotes")
        val quotes: String,

        @SerializedName("relation")
        val relation: Int,

        @SerializedName("relatives")
        val relatives: List<Any>,

        @SerializedName("schools")
        val schools: List<Any>,

        @SerializedName("screen_name")
        val screenName: String,

        @SerializedName("sex")
        val sex: Int,

        @SerializedName("site")
        val site: String,

        @SerializedName("status")
        val status: String,

        @SerializedName("tv")
        val tv: String,

        @SerializedName("universities")
        val universities: List<Any>,

        @SerializedName("university")
        val university: Int,

        @SerializedName("university_name")
        val universityName: String,

        @SerializedName("verified")
        val verified: Int

) {

    companion object {

        fun allFields() = "photo_id, verified, sex, bdate, city, country, home_town, has_photo, photo_50, photo_100, photo_200_orig, photo_200, photo_400_orig, photo_max, photo_max_orig, online, domain, has_mobile, contacts, site, education, universities, schools, status, last_seen, followers_count, common_count, occupation, nickname, relatives, relation, personal, connections, exports, activities, interests, music, movies, tv, books, games, about, quotes, can_post, can_see_all_posts, can_see_audio, can_write_private_message, can_send_friend_request, is_favorite, is_hidden_from_feed, timezone, screen_name, maiden_name, crop_photo, is_friend, friend_status, career, military, blacklisted, blacklisted_by_me, can_be_invited_group"
    }
}