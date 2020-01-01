package com.example.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.room.EntryRepo
import com.example.todo.room.EntryRoomDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EntryRepo

    val entries: LiveData<List<Entry>>

    init {
        val entryDao = EntryRoomDatabase.getDatabase(application).dao()
        repository = EntryRepo(entryDao)
        entries = repository.allEntries
    }

    fun insert(text: String) = viewModelScope.launch {
        val entry = Entry(EVENT, text, entries.value!!.size)
        repository.insert(entry)
    }

    fun delete(position: Int) = viewModelScope.launch {
        repository.delete(position)
    }

    fun next(position: Int) = viewModelScope.launch {
        repository.next(position)
    }

    fun swap(from: Int, to: Int) = viewModelScope.launch {
        repository.swap(from, to)
    }

}