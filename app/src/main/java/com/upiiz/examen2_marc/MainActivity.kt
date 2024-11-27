package com.upiiz.examen2_marc

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.upiiz.examen2_marc.data.UsuariosDatabase
import com.upiiz.examen2_marc.entities.Usuario
import com.upiiz.examen2_marc.entities.UsuarioEntity
import com.upiiz.examen2_marc.repositories.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val usuarioRepository = UsuarioRepository()
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var userAdapter2: UserAdapter2
    private val userList = mutableListOf<UsuarioEntity>()
    private val userList2 = mutableListOf<Usuario>()
    private lateinit var petDatabase: UsuariosDatabase // Base de datos
    private var imgPetReference: ImageView? = null // Referencia temporal para la imagen seleccionada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar base de datos
        petDatabase = UsuariosDatabase.getInstancia(this)

        // Configuración de RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Decide qué método ejecutar
        if (isNetworkAvailable()) {
            // Inicializa el adaptador con acciones habilitadas
            userAdapter = UserAdapter(
                userList,
                onPetClick = { usuario -> showPosts(usuario) }, // Acción habilitada
                onUserLongClick = { usuario -> addUsuario(usuario) } // Acción habilitada
            )
            recyclerView.adapter = userAdapter
            loadUsuariosFromAPI()
        } else {
            // Inicializa el adaptador con acciones deshabilitadas
            userAdapter2 = UserAdapter2(
                userList2,
                onPetClick = { usuario ->
                    Toast.makeText(this, "No hay conexión a Internet", Toast.LENGTH_SHORT).show()
                },
                onUserLongClick = { usuario ->
                    Toast.makeText(this, "No hay conexión a Internet", Toast.LENGTH_SHORT).show()
                }
            )
            recyclerView.adapter = userAdapter2
            loadUsuariosFromRoom()
        }
    }



    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun loadUsuariosFromAPI() {
        lifecycleScope.launch {
            try {
                // Obtenemos la respuesta del repositorio
                val usuarios: List<UsuarioEntity> = withContext(Dispatchers.IO) {
                    usuarioRepository.getAllHeroes()
                }
                // Convertimos la respuesta a la lista esperada de HeroeEntity


                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Listado de ussuarios cargado correctamente.", Toast.LENGTH_LONG).show()
                    userList.clear()
                    userList.addAll(usuarios)
                    userAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("RecyclerViewActivity", "Error al cargar los usuarios de la API", e) // Imprime el error en el Logcat
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun loadUsuariosFromRoom() {
        lifecycleScope.launch {
            petDatabase.mascotaDao().getAll().collect { petsFromDb ->
                userList2.clear()
                userList2.addAll(petsFromDb)
                userAdapter2.notifyDataSetChanged()
            }
        }
    }

    private fun addUsuario(usuario: UsuarioEntity) {
        val name = usuario.name
        val username = usuario.username
        val email = usuario.email
        val newUsuario = Usuario(
            name = name,
            username = username,
            email = email
        )
        lifecycleScope.launch(Dispatchers.IO) {
            petDatabase.mascotaDao().add(newUsuario)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Usuario guardado exitosamente", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun showPosts(usuario: UsuarioEntity) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra("id", usuario.id)
        startActivity(intent)

    }



}
