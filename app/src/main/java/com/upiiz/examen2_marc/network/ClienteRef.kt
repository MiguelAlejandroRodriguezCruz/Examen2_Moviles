package com.upiiz.examen2_marc.network

import com.upiiz.examen2_marc.services.CommentsService
import com.upiiz.examen2_marc.services.PostService
import com.upiiz.examen2_marc.services.UsuarioServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteRef {
    private val BASE_URL="https://jsonplaceholder.typicode.com/"

    val getInstanciaRetofit: UsuarioServices by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsuarioServices::class.java)
    }

    val getInstanciaRetofit2: PostService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostService::class.java)
    }

    val getInstanciaRetofit3: CommentsService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommentsService::class.java)
    }
}