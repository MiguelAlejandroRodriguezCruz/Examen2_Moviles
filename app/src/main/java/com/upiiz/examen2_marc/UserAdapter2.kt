package com.upiiz.examen2_marc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upiiz.examen2_marc.entities.Usuario

class UserAdapter2 (
    private val userList: MutableList<Usuario>,
    private val onPetClick: (Usuario) -> Unit,    // Callback para click corto
    private val onUserLongClick: (Usuario) -> Unit // Callback para click largo
) : RecyclerView.Adapter<UserAdapter2.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val user = userList[position]

        holder.txtName.text = user.name
        holder.txtPost.text = user.username
        holder.txtComentario.text = user.email

        // Configurar eventos de click corto y largo
        holder.itemView.setOnClickListener {
            onPetClick(user) // Llamar al callback de click corto
        }
        holder.itemView.setOnLongClickListener {
            onUserLongClick(user) // Llamar al callback de click largo
            true // Retornar true para indicar que se manejó el evento
        }
    }

    override fun getItemCount(): Int = userList.size

    class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtNombre)
        val txtPost: TextView = itemView.findViewById(R.id.txtUserName)
        val txtComentario: TextView = itemView.findViewById(R.id.txtEmail)
    }
}
