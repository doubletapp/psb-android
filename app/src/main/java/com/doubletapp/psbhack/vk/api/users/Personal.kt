package com.doubletapp.psbhack.vk.api.users

import com.google.gson.annotations.SerializedName

data class Personal(

        @SerializedName("alcohol")
        val alcohol: Int,

        @SerializedName("inspired_by")
        val inspiredBy: String,

        @SerializedName("life_main")
        val lifeMain: Int,

        @SerializedName("people_main")
        val peopleMain: Int,

        @SerializedName("religion")
        val religion: String,

        @SerializedName("religion_id")
        val religionId: Int,

        @SerializedName("smoking")
        val smoking: Int

)