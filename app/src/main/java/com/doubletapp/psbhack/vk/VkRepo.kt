package com.doubletapp.psbhack.vk

import com.doubletapp.psbhack.vk.api.goods.VkGood
import com.doubletapp.psbhack.vk.api.groups.VkGroup
import com.doubletapp.psbhack.vk.api.notes.VkNote
import com.doubletapp.psbhack.vk.api.posts.VkPost
import com.doubletapp.psbhack.vk.api.users.VkUser
import com.google.gson.Gson
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.requests.VKRequest
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.util.LinkedList

class VkRepo(

        val token: VKAccessToken

) {

    private val gson by lazy { Gson() }

    fun getUserProfile(): Single<VkUser> {
        val request = object : VKRequest<List<VkUser>>("users.get") {

            init {
                addParam("fields", VkUser.allFields())
            }

            override fun parse(r: JSONObject): List<VkUser> {
                val response = r.getJSONArray("response")
                val result = LinkedList<VkUser>()
                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    result.addLast(gson.fromJson(obj.toString(), VkUser::class.java))
                }
                return result.toList()
            }
        }

        return Single
                .fromCallable { VK.executeSync(request) }
                .subscribeOn(Schedulers.io())
                .map { it[0] }
    }

    fun getUserEmail(): Single<String> = Single.fromCallable { token.email }

    fun getUserStatus(): Single<String> {
        val request = object : VKRequest<String>("status.get") {

            override fun parse(r: JSONObject): String {
                return r.getJSONObject("response").getString("text")
            }
        }

        return Single
                .fromCallable { VK.executeSync(request) }
                .subscribeOn(Schedulers.io())
    }

    fun getUserGroupsRaw(): Single<String> {
        val request = object : VKRequest<String>("groups.get") {

            init {
                addParam("extended", 1)
                addParam("fields", VkGroup.allFields())
            }

            override fun parse(r: JSONObject): String {
                return r.getJSONObject("response").getJSONArray("items").toString()
            }
        }

        return Single
                .fromCallable { VK.executeSync(request) }
                .subscribeOn(Schedulers.io())
    }

    fun getUserGroups(): Single<List<VkGroup>> {
        val request = object : VKRequest<List<VkGroup>>("groups.get") {

            init {
                addParam("extended", 1)
                addParam("fields", VkGroup.allFields())
            }

            override fun parse(r: JSONObject): List<VkGroup> {
                val response = r.getJSONObject("response").getJSONArray("items")
                val result = LinkedList<VkGroup>()
                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    result.addLast(gson.fromJson(obj.toString(), VkGroup::class.java))
                }
                return result.toList()
            }
        }

        return Single
                .fromCallable { VK.executeSync(request) }
                .subscribeOn(Schedulers.io())
    }

    fun getUserGoodsRaw(): Single<String> {
        val request = object : VKRequest<String>("market.get") {

            init {
                addParam("extended", 1)
                token.userId?.let {
                    addParam("owner_id", it)
                }
            }

            override fun parse(r: JSONObject): String {
                return r.getJSONObject("response").getJSONArray("items").toString()
            }
        }

        return Single
                .fromCallable { VK.executeSync(request) }
                .subscribeOn(Schedulers.io())
    }

    fun getUserGoods(): Single<List<VkGood>> {
        val request = object : VKRequest<List<VkGood>>("market.get") {

            init {
                addParam("extended", 1)
                token.userId?.let {
                    addParam("owner_id", it)
                }
            }

            override fun parse(r: JSONObject): List<VkGood> {
                val response = r.getJSONObject("response").getJSONArray("items")
                val result = LinkedList<VkGood>()
                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    result.addLast(gson.fromJson(obj.toString(), VkGood::class.java))
                }
                return result.toList()
            }
        }

        return Single
                .fromCallable { VK.executeSync(request) }
                .subscribeOn(Schedulers.io())
    }

    fun getUserPostsRaw(): Single<String> {
        val request = object : VKRequest<String>("wall.get") {

            init {
                addParam("extended", 1)
            }

            override fun parse(r: JSONObject): String {
                return r.getJSONObject("response").getJSONArray("items").toString()
            }
        }

        return Single
                .fromCallable { VK.executeSync(request) }
                .subscribeOn(Schedulers.io())
    }

    fun getUserPosts(): Single<List<VkPost>> {
        val request = object : VKRequest<List<VkPost>>("wall.get") {

            init {
                addParam("extended", 1)
            }

            override fun parse(r: JSONObject): List<VkPost> {
                val response = r.getJSONObject("response").getJSONArray("items")
                val result = LinkedList<VkPost>()
                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    result.addLast(gson.fromJson(obj.toString(), VkPost::class.java))
                }
                return result.toList()
            }
        }

        return Single
                .fromCallable { VK.executeSync(request) }
                .subscribeOn(Schedulers.io())
    }

    fun getUserNotesRaw(): Single<String> {
        val request = object : VKRequest<String>("notes.get") {

            override fun parse(r: JSONObject): String {
                return r.getJSONObject("response").getJSONArray("items").toString()
            }
        }

        return Single
                .fromCallable { VK.executeSync(request) }
                .subscribeOn(Schedulers.io())
    }

    fun getUserNotes(): Single<List<VkNote>> {
        val request = object : VKRequest<List<VkNote>>("notes.get") {

            override fun parse(r: JSONObject): List<VkNote> {
                val response = r.getJSONObject("response").getJSONArray("items")
                val result = LinkedList<VkNote>()
                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    result.addLast(gson.fromJson(obj.toString(), VkNote::class.java))
                }
                return result.toList()
            }
        }

        return Single
                .fromCallable { VK.executeSync(request) }
                .subscribeOn(Schedulers.io())
    }
}