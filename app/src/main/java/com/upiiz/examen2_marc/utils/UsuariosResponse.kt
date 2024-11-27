package com.upiiz.examen2_marc.utils

import com.upiiz.examen2_marc.entities.UsuarioEntity

data class UsuariosResponse(
    val estado: Int,
    val msg: String,
    val usuarios: List<UsuarioEntity>
)
