package com.upiiz.examen2_marc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upiiz.examen2_marc.data.UsuariosDatabase
import com.upiiz.examen2_marc.entities.PostEntity
import com.upiiz.examen2_marc.entities.UsuarioEntity
import com.upiiz.examen2_marc.repositories.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostActivity : AppCompatActivity() {

    private val postRepository = PostRepository()
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private val postList = mutableListOf<PostEntity>()
    private lateinit var petDatabase: UsuariosDatabase // Base de datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val id = intent.getLongExtra("id", -1L)

        // Inicializar base de datos
        petDatabase = UsuariosDatabase.getInstancia(this)

        // Configuración de RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        postAdapter = PostAdapter(
            postList,
            onPostClick = { post -> showCommentDialog(post) },
            onPostLongClick = { post -> showEditPetDialog(post) }
        )
        recyclerView.adapter = postAdapter

        recyclerView.adapter = postAdapter

        loadPostsFromAPI(id)


    }

    private fun loadPostsFromAPI(id: Long) {
        lifecycleScope.launch {
            try {
                // Obtenemos la respuesta del repositorio
                val posts: List<PostEntity> = withContext(Dispatchers.IO) {
                    postRepository.getAllHeroes(id)
                }
                // Convertimos la respuesta a la lista esperada de HeroeEntity


                withContext(Dispatchers.Main) {
                    Toast.makeText(this@PostActivity, "Listado de posts cargado correctamente.", Toast.LENGTH_LONG).show()
                    postList.clear()
                    postList.addAll(posts)
                    postAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("RecyclerViewActivity", "Error al cargar los posts de la API", e) // Imprime el error en el Logcat
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@PostActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun showCommentDialog(post: PostEntity) {
        val intent = Intent(this, CommentsActivity::class.java)
        intent.putExtra("id", post.id)
        startActivity(intent)
    }


    private fun showEditPetDialog(post: PostEntity) {

    }



}
