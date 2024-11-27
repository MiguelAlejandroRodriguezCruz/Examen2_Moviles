package com.upiiz.examen2_marc.repositories

import com.upiiz.examen2_marc.network.ClienteRef
import com.upiiz.examen2_marc.entities.UsuarioEntity
import com.upiiz.examen2_marc.services.UsuarioServices

class UsuarioRepository(private val usuarioServices: UsuarioServices= ClienteRef.getInstanciaRetofit) {
    suspend fun getAllHeroes(): List<UsuarioEntity> {
        return usuarioServices.getAllHeroes()
    }
    suspend fun getHeroeById(id:Long): UsuarioEntity {
        return usuarioServices.getAllHeroeById(id)
    }
}