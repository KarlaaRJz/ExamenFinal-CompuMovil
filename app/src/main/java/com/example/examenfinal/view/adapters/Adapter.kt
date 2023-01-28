package com.example.examenfinal.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examenfinal.databinding.ElementBinding
import com.example.examenfinal.model.Raza
import com.example.examenfinal.view.activities.MainActivity

class Adapter(private val context: Context, private val raza: ArrayList<Raza>): RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(view: ElementBinding): RecyclerView.ViewHolder(view.root) {
        val ivThumbnail = view.ivThumbnail
        val tvTitle = view.tvTitle
        val tvDeveloper = view.tvDeveloper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = raza[position].raza

        Glide.with(context)
            .load(raza[position].thumbnail)
            .into(holder.ivThumbnail)

        holder.tvDeveloper.text = "KarlaRJz"

        holder.itemView.setOnClickListener {
            if(context is MainActivity) context.selectedRaza(raza[position])
        }
    }

    override fun getItemCount(): Int = raza.size
}