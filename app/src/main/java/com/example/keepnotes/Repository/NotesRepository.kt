package com.example.keepnotes.Repository

import androidx.lifecycle.LiveData
import com.example.keepnotes.Dao.NotesDao
import com.example.keepnotes.Model.NotesEntity

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes():LiveData<List<NotesEntity>> {
        return  dao.getNotes()
    }

    fun getHighNotes():LiveData<List<NotesEntity>> {
        return dao.getAllHighNotes()
    }

    fun getMediumNotes():LiveData<List<NotesEntity>> {
        return dao.getAllMediumNotes()
    }

    fun getLowNotes():LiveData<List<NotesEntity>> {
        return dao.getAllLowNotes()
    }

    fun inserNotes(notes:NotesEntity){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int) {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: NotesEntity) {
        dao.updateNotes(notes)
    }
}