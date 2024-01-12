package com.example.notes.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidkotlinappnote.model.ModelNote


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: ModelNote)
    @Update
    suspend fun updateNote(note: ModelNote)
    @Delete
    suspend fun deleteNote(note: ModelNote)

    @Query("select * from NOTES order by id desc")
    fun getAllNotes(): LiveData<List<ModelNote>>

}