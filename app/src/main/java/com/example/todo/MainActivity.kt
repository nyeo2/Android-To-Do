package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: Adapter
    private lateinit var dataSet: MutableList<Entry>
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = this.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        editText = this.findViewById(R.id.editText)
        dataSet = mutableListOf(Entry(Entry.Type.Event, "Eat food"), Entry(Entry.Type.Note, "Wash dishes"), Entry(Entry.Type.Task, "Fight fires"))

        recyclerAdapter = Adapter(dataSet)
        recyclerView.adapter = recyclerAdapter

        val touchHelper = ItemTouchHelper(ItemMoveCallback(recyclerAdapter))
        touchHelper.attachToRecyclerView(recyclerView)

        this.findViewById<Button>(R.id.enter_button).setOnClickListener {
            addTask()
        }
    }
    private fun addTask(){
        dataSet.add(Entry(Entry.Type.Event, editText.text.toString()))
        recyclerView.adapter?.notifyItemChanged(dataSet.size-1)
        editText.setText("")
    }
}
