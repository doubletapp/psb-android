package com.doubletapp.psbhack.auth.api

import com.google.gson.annotations.SerializedName

data class CheckResponseBody(

        @SerializedName("request_id")
        val requestId: String
)