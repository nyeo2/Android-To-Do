package com.example.todo.room

import androidx.lifecycle.LiveData
import com.example.todo.Entry

class EntryRepo(private val Dao: DbDao) {

    val allEntries: LiveData<List<Entry>> = Dao.getEntries()

    suspend fun insert(entry: Entry) {
        Dao.insert(entry)
    }
    suspend fun delete(position: Int) {
        Dao.delete(position)
    }
    suspend fun next(position: Int){
        Dao.next(position)
    }

    suspend fun swap(from: Int, to: Int){
        Dao.swap(from, to)
    }

}