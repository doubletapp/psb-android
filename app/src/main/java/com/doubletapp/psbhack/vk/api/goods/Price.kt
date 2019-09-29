package com.doubletapp.psbhack.vk.api.goods

import com.doubletapp.psbhack.vk.api.goods.Currency
import com.google.gson.annotations.SerializedName

data class Price(

    @SerializedName("amount")
        val amount: String,

    @SerializedName("currency")
        val currency: Currency,

    @SerializedName("text")
        val text: String

)