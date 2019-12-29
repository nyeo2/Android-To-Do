package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dataSet: MutableList<Entry>) :
        RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val bullet: TextView = v.findViewById(R.id.bullet)
        val textView: TextView = v.findViewById(R.id.textView)
    }
    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bullet.text = when (dataSet[position].type){
            Entry.Type.Task -> "•"
            Entry.Type.Note -> "⁃"
            Entry.Type.Event -> "◦"
        }

        holder.bullet.setOnClickListener {
            dataSet[holder.adapterPosition].next()
            this.notifyItemChanged(holder.adapterPosition)
        }

        holder.textView.text = dataSet[position].text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_view, parent, false)
        return ViewHolder(v)
    }

    fun onRowMoved(from: Int, to: Int){
        val temp = dataSet[to]
        dataSet[from] = dataSet[to]
        dataSet[to] = temp
        notifyItemMoved(from, to)
    }

    fun delete(position: Int){
        dataSet.removeAt(position)
        this.notifyItemRemoved(position)
    }
}