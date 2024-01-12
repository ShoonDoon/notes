package com.example.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.ModelNote
import com.example.notes.Repository
import kotlinx.coroutines.launch

class ViewModel (app: Application, private  val repository: Repository): AndroidViewModel(app){

    fun addNote(note: ModelNote) =
        viewModelScope.launch {
            repository.insertNote(note)
        }
    fun deleteNote(note: ModelNote) =
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    fun updateNote(note: ModelNote) =
        viewModelScope.launch {
            repository.updateNote(note)
        }
    fun getAllNotes() = repository. getAllNotes()
}