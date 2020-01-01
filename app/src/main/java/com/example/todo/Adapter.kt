package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val viewModel: MainViewModel) :
        RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var entries = ArrayList<Entry>()

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val bullet: TextView = v.findViewById(R.id.bullet)
        val textView: TextView = v.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bullet.text = when (entries[position].type){
            TASK -> "•"
            NOTE -> "⁃"
            EVENT -> "◦"
            else -> "ERROR"
        }

        holder.bullet.setOnClickListener {
            val id = viewModel.entries.value!![holder.adapterPosition].id
            viewModel.next(id)
            this.notifyItemChanged(holder.adapterPosition)
        }

        holder.textView.text = entries[position].text
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    fun setEntries(entries: List<Entry>){
        this.entries = ArrayList(entries)
        notifyDataSetChanged()
    }
}