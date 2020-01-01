package com.example.todo.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo.Entry

@Dao
interface DbDao {


    @Query("SELECT * from EntryTable ORDER BY position")
    fun getEntries(): LiveData<List<Entry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entry: Entry)

    @Query("DELETE from EntryTable Where position=:position")
    suspend fun delete(position: Int)

    @Query("UPDATE EntryTable Set type = (type + 1) % 3 Where position=:position")
    suspend fun next(position: Int)

    @Query("UPDATE EntryTable SET position = (:from + :to - position) WHERE position IN (:from, :to)")
    suspend fun swap(from: Int, to: Int)
}