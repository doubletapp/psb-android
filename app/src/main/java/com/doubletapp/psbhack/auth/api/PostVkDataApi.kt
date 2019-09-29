package com.doubletapp.psbhack.auth.api

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PostVkDataApi {

    @POST("/check_self_employed")
    @Headers("Content-type: application/json")
    fun postVkData(@Body body: PostVkDataBody): Single<PostVkDataResult>

    @POST("/check_request")
    @Headers("Content-type: application/json")
    fun checkResponse(@Body body: CheckResponseBody): Single<CheckResponseResult>
}