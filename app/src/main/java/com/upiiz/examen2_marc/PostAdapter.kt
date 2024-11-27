package com.upiiz.examen2_marc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upiiz.examen2_marc.entities.PostEntity

class PostAdapter (
    private val postList: MutableList<PostEntity>,
    private val onPostClick: (PostEntity) -> Unit,    // Callback para click corto
    private val onPostLongClick: (PostEntity) -> Unit // Callback para click largo
) : RecyclerView.Adapter<PostAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val post = postList[position]

        holder.txtTitle.text = post.title
        holder.txtBody.text = post.body

        // Configurar eventos de click corto y largo
        holder.itemView.setOnClickListener {
            onPostClick(post) // Llamar al callback de click corto
        }
        holder.itemView.setOnLongClickListener {
            onPostLongClick(post) // Llamar al callback de click largo
            true // Retornar true para indicar que se manejó el evento
        }
    }

    override fun getItemCount(): Int = postList.size

    class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtBody: TextView = itemView.findViewById(R.id.txtBody)
    }
}