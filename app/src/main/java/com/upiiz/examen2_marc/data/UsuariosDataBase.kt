package com.upiiz.examen2_marc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.upiiz.examen2_marc.entities.Usuario

@Database(
    entities = [Usuario::class],
    version = 1
)
abstract class UsuariosDatabase: RoomDatabase() {
    abstract fun mascotaDao():UsuarioDao

    //Definimos un singleton - tener solo una instancia
    companion object{
        @Volatile
        private var INSTANCIA:UsuariosDatabase?=null

        fun getInstancia(contexto:Context):UsuariosDatabase{
            val tempDB = INSTANCIA

            if(tempDB!=null){
                return tempDB
            }

            //solo se tenga un acceso al mismo tiempo, solicitudes concurrentes
            //solo el metodo 1 tenga la facultad de crearlo
            synchronized(this){
                //generamos la instancia de la base de datos
                val instancia = Room.databaseBuilder(
                    contexto.applicationContext,
                    UsuariosDatabase::class.java,
                    "usuario"
                ).fallbackToDestructiveMigration() // Esto destruye la base de datos si la versión cambia
                    .build()
//patron - factory

                INSTANCIA=instancia
                return instancia
            }
        }
    }
}