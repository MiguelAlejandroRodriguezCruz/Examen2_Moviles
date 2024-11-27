package com.upiiz.examen2_marc.repositories

import com.upiiz.examen2_marc.network.ClienteRef
import com.upiiz.examen2_marc.entities.PostEntity
import com.upiiz.examen2_marc.services.PostService

class PostRepository(private val postServices: PostService= ClienteRef.getInstanciaRetofit2) {
    suspend fun getAllHeroes(id: Long): List<PostEntity> {
        return postServices.getAllPosts(id)
    }
    suspend fun getHeroeById(id:Long): PostEntity {
        return postServices.getAllPostById(id)
    }
}