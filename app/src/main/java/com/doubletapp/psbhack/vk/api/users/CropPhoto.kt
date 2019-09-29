package com.doubletapp.psbhack.vk.api.users

import com.google.gson.annotations.SerializedName

data class CropPhoto(

        @SerializedName("crop")
        val crop: Crop,

        @SerializedName("photo")
        val photo: Photo,

        @SerializedName("rect")
        val rect: Rect

)