package com.example.todo.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo.Entry

@Dao
interface DbDao {


    @Query("SELECT * from EntryTable ORDER BY id")
    fun getEntries(): LiveData<List<Entry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entry: Entry)

    @Query("DELETE from EntryTable Where id=:id")
    suspend fun delete(id: Int)

    @Query("UPDATE EntryTable Set type = (type + 1) % 3 Where id=:id")
    suspend fun next(id: Int)

    @Query("UPDATE EntryTable Set id = Case id when :fromId Then :toId when :toId then :fromId Else id End")
    suspend fun swap(fromId: Int, toId:Int)
}