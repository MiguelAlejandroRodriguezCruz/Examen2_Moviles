package com.upiiz.examen2_marc.services

import com.upiiz.examen2_marc.entities.CommentsEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentsService {
    @GET("posts/{id}/comments")
    suspend fun getAllComments(@Path("id") id:Long): List<CommentsEntity>
    @GET("posts/{id}/comments/{id}")
    suspend fun getAllCommentsById(@Path("id") id:Long): CommentsEntity
}