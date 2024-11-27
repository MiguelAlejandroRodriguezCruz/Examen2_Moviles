package com.upiiz.examen2_marc.services

import com.upiiz.examen2_marc.entities.PostEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("users/{id}/posts")
    suspend fun getAllPosts(@Path("id") id:Long): List<PostEntity>

    @GET("users/{id}/posts/{id}")
    suspend fun getAllPostById(@Path("id") id:Long): PostEntity

}