package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class Adapter(private val viewModel: MainViewModel) :
        RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var entries = ArrayList<Entry>()
    private var deleted = ArrayList<Entry>()

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val bullet: TextView = v.findViewById(R.id.bullet)
        val textView: TextView = v.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bullet.text = when (entries[position].type) {
            TASK -> "•"
            NOTE -> "⁃"
            EVENT -> "◦"
            else -> "ERROR"
        }

        holder.bullet.setOnClickListener {
            this.notifyItemChanged(holder.adapterPosition)
        }

        holder.textView.text = entries[position].text
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    fun setEntries(entries: List<Entry>) {
        this.entries = ArrayList(entries)
        notifyDataSetChanged()
    }

    fun delete(position:Int) {
        deleted.add(entries[position])
        entries.removeAt(position)
        notifyItemRemoved(position)
    }

    fun swap(from:Int, to:Int) {
        val temp = entries[from]
        entries[from] = entries[to]
        entries[to] = temp
        notifyItemMoved(from, to)
    }

    fun insert(text: String){
        viewModel.insert(Entry(EVENT, text, entries.size))
        notifyItemInserted(entries.size-1)
    }

    fun update(){
        for (entry in entries) {
            entry.position = entries.indexOf(entry)
            viewModel.update(entry)
        }
    }

    fun deleteDB(){
        if (deleted != null) {
            for (i in 0 until deleted.size) {
                viewModel.delete(deleted[i])
            }
        }
    }
}