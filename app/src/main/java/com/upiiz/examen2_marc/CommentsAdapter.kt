package com.upiiz.examen2_marc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upiiz.examen2_marc.entities.CommentsEntity

class CommentsAdapter (
    private val commentsList: MutableList<CommentsEntity>,
    private val onCommentsClick: (CommentsEntity) -> Unit,    // Callback para click corto
    private val onCommentsLongClick: () -> Unit // Callback para click largo
) : RecyclerView.Adapter<CommentsAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comments, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val comment = commentsList[position]

        holder.txtName.text = comment.name
        holder.txtAddress.text = comment.email
        holder.txtMensaje.text = comment.body

        // Configurar eventos de click corto y largo
        holder.itemView.setOnClickListener {
            onCommentsClick(comment) // Llamar al callback de click corto
        }
        holder.itemView.setOnLongClickListener {
            onCommentsLongClick() // Llamar al callback de click largo
            true // Retornar true para indicar que se manejó el evento
        }
    }

    override fun getItemCount(): Int = commentsList.size

    class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtAddress: TextView = itemView.findViewById(R.id.txtAddress)
        val txtMensaje: TextView = itemView.findViewById(R.id.txtmensaje)
    }
}