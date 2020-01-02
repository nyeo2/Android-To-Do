package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: Adapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel(application)::class.java)
        mainViewModel.entries.observe(this, Observer { entries -> entries?.let {recyclerAdapter.setEntries(it)} })


        recyclerView = this.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        editText = this.findViewById(R.id.editText)

        recyclerAdapter = Adapter(mainViewModel)
        recyclerView.adapter = recyclerAdapter

        val touchHelper = ItemTouchHelper(ItemMoveCallback(recyclerAdapter))
        touchHelper.attachToRecyclerView(recyclerView)

        this.findViewById<Button>(R.id.enter_button).setOnClickListener {
            recyclerAdapter.insert(editText.text.toString())
            editText.text.clear()
        }
    }

    override fun onPause() {
        super.onPause()
        recyclerAdapter.update()
        Timber.i("Delete and update stuff")

    }
}
