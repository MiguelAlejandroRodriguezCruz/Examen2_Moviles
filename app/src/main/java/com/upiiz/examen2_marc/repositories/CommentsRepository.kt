package com.upiiz.examen2_marc.repositories

import com.upiiz.examen2_marc.entities.CommentsEntity
import com.upiiz.examen2_marc.network.ClienteRef
import com.upiiz.examen2_marc.services.CommentsService

class CommentsRepository(private val commentsServices: CommentsService = ClienteRef.getInstanciaRetofit3) {
    suspend fun getAllHeroes(id: Long): List<CommentsEntity> {
        return commentsServices.getAllComments(id)
    }
    suspend fun getHeroeById(id:Long): CommentsEntity {
        return commentsServices.getAllCommentsById(id)
    }
}