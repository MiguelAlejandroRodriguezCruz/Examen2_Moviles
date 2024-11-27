package com.upiiz.examen2_marc.entities

data class CommentsEntity(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)
