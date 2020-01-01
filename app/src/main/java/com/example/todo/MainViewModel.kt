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

    fun insert(entry: Entry) = viewModelScope.launch {
        repository.insert(entry)
    }

    fun delete(id: Int) = viewModelScope.launch {
        repository.delete(id)
    }

    fun next(id: Int) = viewModelScope.launch {
        repository.next(id)
    }

    fun swap(fromId: Int, toId: Int) = viewModelScope.launch {
        repository.swap(fromId, toId)
    }
}