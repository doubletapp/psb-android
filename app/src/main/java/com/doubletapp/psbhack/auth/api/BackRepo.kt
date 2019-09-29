package com.doubletapp.psbhack.auth.api

import com.doubletapp.psbhack.BuildConfig
import com.doubletapp.psbhack.vk.VkRepo
import com.vk.api.sdk.auth.VKAccessToken
import io.reactivex.Single
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

object BackRepo {

    private val postVkDataApi by lazy {
        val logger = HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }

        val okHttp = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(20L, TimeUnit.SECONDS)
                .readTimeout(20L, TimeUnit.SECONDS)
                .writeTimeout(20L, TimeUnit.SECONDS)
                .hostnameVerifier(HostnameVerifier { hostname, session ->
                    hostname.equals(session.peerHost, true)
                })
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://psb.doubletapp.ru")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttp)
                .build()

        retrofit.create(PostVkDataApi::class.java)
    }

    fun postToken(token: VKAccessToken): Single<PostVkDataResult> {
        val vkRepo = VkRepo(token)
        val userGroupps = vkRepo.getUserGroupsRaw()
        val userGoods = vkRepo.getUserGoodsRaw()
        val userPosts = vkRepo.getUserPostsRaw()
        val userNotes = vkRepo.getUserNotesRaw()
        return Single
                .zip<String, String, String, String, PostVkDataBodyDataModel>(
                        userGroupps, userGoods, userPosts, userNotes,
                        Function4 { groups, goods, posts, notes ->
                            PostVkDataBodyDataModel(
                                    groups = groups,
                                    goods = goods,
                                    posts = posts,
                                    notes = notes
                            )
                        })
                .subscribeOn(Schedulers.io())
                .flatMap { data ->
                    postVkDataApi.postVkData(PostVkDataBody(
                            userId = token.userId?.toString().orEmpty(),
                            data = data
                    ))
                }

    }

    fun checkResponse(requestId: String): Single<CheckResponseResult> {
        return postVkDataApi
                .checkResponse(CheckResponseBody(requestId))
                .subscribeOn(Schedulers.io())
                .compose {
                    it.doOnSuccess { result ->
                        if (!result.result) {
                            throw Throwable()
                        }
                    }
                }
                .retryWhen { it.delay(5, TimeUnit.SECONDS) }
    }
}