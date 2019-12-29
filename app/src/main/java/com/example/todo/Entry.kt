package com.example.todo

data class Entry(var type: Entry.Type, val text: String)  {
    enum class Type{
        Task, Event, Note
    }
    fun next(){
        type = when (type){
            Type.Task -> Type.Event
            Type.Event -> Type.Note
            Type.Note -> Type.Task
        }
    }
}