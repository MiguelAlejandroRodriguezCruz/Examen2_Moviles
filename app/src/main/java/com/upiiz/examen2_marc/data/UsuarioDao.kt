package com.upiiz.examen2_marc.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.upiiz.examen2_marc.entities.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    // Obtener todas las mascotas
    @Query("SELECT * FROM usuarios")
    fun getAll(): Flow<List<Usuario>>

    // Obtener una mascota por su ID
    @Query("SELECT * FROM usuarios WHERE id = :id")
    fun getById(id: Long): Flow<Usuario>

    // Insertar una nueva mascota
    @Insert
    suspend fun add(usuario: Usuario): Long

    // Actualizar una mascota existente
    @Update
    suspend fun update(usuario: Usuario)

    // Eliminar una mascota
    @Delete
    suspend fun delete(usuario: Usuario)

    @Insert
    suspend fun addAll(usuario: List<Usuario>)

}


