package com.example.todo.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo.Entry

@Dao
interface DbDao {

    @Insert
    suspend fun insert(entry: Entry)

    @Delete
    suspend fun delete(entry: Entry)

    @Update
    suspend fun update(entry: Entry)

    @Query("SELECT * from entryTable ORDER BY position ASC")
    fun getEntries(): LiveData<List<Entry>>

    @Query("UPDATE entryTable SET type = (type + 1) % 3 where id = :id")
    suspend fun next(id: Int)
}