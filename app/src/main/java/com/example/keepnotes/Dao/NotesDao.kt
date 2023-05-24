package com.example.keepnotes.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.keepnotes.Model.NotesEntity

@Dao
interface NotesDao {

    @Query("SELECT * FROM  Notes")
    fun  getNotes():LiveData<List<NotesEntity>>

    @Query("SELECT * FROM  Notes WHERE priority =3")
    fun  getAllHighNotes():LiveData<List<NotesEntity>>

    @Query("SELECT * FROM  Notes WHERE priority =2")
    fun  getAllMediumNotes():LiveData<List<NotesEntity>>

    @Query("SELECT * FROM  Notes WHERE priority =1")
    fun  getAllLowNotes():LiveData<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notesEntity: NotesEntity)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id:Int)

    @Update
    fun updateNotes(notesEntity: NotesEntity)
}