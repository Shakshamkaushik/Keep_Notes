package com.example.keepnotes.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.keepnotes.Dao.NotesDao
import com.example.keepnotes.Model.NotesEntity

@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun myNotesDao(): NotesDao

    companion object {
        @Volatile
        var INSTANCE: NotesDataBase? = null

        fun getDatabaseInstance(context: Context): NotesDataBase {
            val tempoaryInstance = INSTANCE
            if (tempoaryInstance != null) {
                return tempoaryInstance
            }
            //only create single instance
            synchronized(this) {
                val roomDataBaseInstance =
                    Room.databaseBuilder(context, NotesDataBase::class.java, "Notes").allowMainThreadQueries().build()

                INSTANCE = roomDataBaseInstance
                return roomDataBaseInstance
            }
        }
    }
}