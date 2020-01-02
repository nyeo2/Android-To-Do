package com.example.todo.room

import androidx.lifecycle.LiveData
import com.example.todo.Entry

class EntryRepo(private val Dao: DbDao) {

    val allEntries: LiveData<List<Entry>> = Dao.getEntries()

    suspend fun insert(entry: Entry) {
        Dao.insert(entry)
    }

    suspend fun delete(entry: Entry) {
        Dao.delete(entry)
    }

    suspend fun update(entry:Entry) {
        Dao.update(entry)
    }

    suspend fun next(entry:Entry) {
        Dao.next(entry.id)
    }
}