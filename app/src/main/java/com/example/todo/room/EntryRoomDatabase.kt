package com.example.todo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.Entry


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Entry::class], version = 1, exportSchema = false)
abstract class EntryRoomDatabase : RoomDatabase() {

    abstract fun dao(): DbDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: EntryRoomDatabase? = null

        fun getDatabase(context: Context): EntryRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntryRoomDatabase::class.java,
                    "entry_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}