package com.example.todo.room

import androidx.lifecycle.LiveData
import com.example.todo.Entry

class EntryRepo(private val Dao: DbDao) {

    val allEntries: LiveData<List<Entry>> = Dao.getEntries()

    suspend fun insert(entry: Entry) {
        Dao.insert(entry)
    }
    suspend fun delete(id: Int){
        Dao.delete(id)
    }
    suspend fun next(id:Int){
        Dao.next(id)
    }

    suspend fun swap(fromId: Int, toId: Int){
        Dao.swap(fromId, toId)
    }
}