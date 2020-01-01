package com.example.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

const val TASK = 0
const val EVENT = 1
const val NOTE = 2

@Entity(tableName = "EntryTable")
class Entry(var type: Int, var text: String){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}