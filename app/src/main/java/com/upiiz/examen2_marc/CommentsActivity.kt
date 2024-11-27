package com.upiiz.examen2_marc

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upiiz.examen2_marc.data.UsuariosDatabase
import com.upiiz.examen2_marc.entities.CommentsEntity
import com.upiiz.examen2_marc.repositories.CommentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentsActivity : AppCompatActivity() {

    private val commentsRepository = CommentsRepository()
    private lateinit var recyclerView: RecyclerView
    private lateinit var commentsAdapter: CommentsAdapter
    private val commentsList = mutableListOf<CommentsEntity>()
    private lateinit var petDatabase: UsuariosDatabase // Base de datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val id = intent.getLongExtra("id", -1L)

        // Inicializar base de datos
        petDatabase = UsuariosDatabase.getInstancia(this)

        // Configuración de RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        commentsAdapter = CommentsAdapter(
            commentsList,
            onCommentsClick = { comments -> showEditPetDialog() },
            onCommentsLongClick = { showDeletePetDialog() }
        )
        recyclerView.adapter = commentsAdapter

        recyclerView.adapter = commentsAdapter

        loadCommentsFromAPI(id)


    }

    private fun loadCommentsFromAPI(id: Long) {
        lifecycleScope.launch {
            try {
                // Obtenemos la respuesta del repositorio
                val comments: List<CommentsEntity> = withContext(Dispatchers.IO) {
                    commentsRepository.getAllHeroes(id)
                }
                // Convertimos la respuesta a la lista esperada de HeroeEntity


                withContext(Dispatchers.Main) {
                    Toast.makeText(this@CommentsActivity, "Listado de Comments cargado correctamente.", Toast.LENGTH_LONG).show()
                    commentsList.clear()
                    commentsList.addAll(comments)
                    commentsAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("RecyclerViewActivity", "Error al cargar los Comments de la API", e) // Imprime el error en el Logcat
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@CommentsActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun showDeletePetDialog() {

    }


    private fun showEditPetDialog() {

    }



}
