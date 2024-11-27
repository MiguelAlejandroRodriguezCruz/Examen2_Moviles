package com.upiiz.examen2_marc.services

import com.upiiz.examen2_marc.entities.UsuarioEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuarioServices {
    @GET("users")
    suspend fun getAllHeroes(): List<UsuarioEntity>

    @GET("users/{id}")
    suspend fun getAllHeroeById(@Path("id") id:Long): UsuarioEntity

}