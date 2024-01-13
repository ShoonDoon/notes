package com.example.notes

import com.example.notes.db.DatabaseBuild
import com.example.notes.db.ModelNote

class Repository(private val db: DatabaseBuild) {


    suspend fun insertNote(note: ModelNote) = db.getNoteDao().insertNote(note)

    suspend fun deleteNote(note: ModelNote) = db.getNoteDao().deleteNote(note)

    suspend fun updateNote(note: ModelNote) = db.getNoteDao().updateNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
}