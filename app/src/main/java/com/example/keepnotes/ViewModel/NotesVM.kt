package com.example.keepnotes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.keepnotes.DataBase.NotesDataBase
import com.example.keepnotes.Model.NotesEntity
import com.example.keepnotes.Repository.NotesRepository

class NotesVM(application: Application): AndroidViewModel(application) {
    val repository: NotesRepository

    init {
        val dao = NotesDataBase.getDatabaseInstance(application).myNotesDao()
        repository= NotesRepository(dao)
    }

    fun addNotes(notes: NotesEntity) {
        repository.inserNotes(notes)
    }

    fun getNotes():LiveData<List<NotesEntity>> = repository.getAllNotes()

    fun deleteNotes(id:Int) {
        repository.deleteNotes(id)
    }

    fun updateNOtes(notes: NotesEntity) {
        repository.updateNotes(notes)
    }

    fun getHighNotes():LiveData<List<NotesEntity>> {
         return repository.getHighNotes()
    }

    fun getMediumNotes():LiveData<List<NotesEntity>> {
        return repository.getMediumNotes()
    }

    fun getLowNotes():LiveData<List<NotesEntity>> {
        return repository.getLowNotes()
    }
}