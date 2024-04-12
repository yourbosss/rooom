package com.bignerdranch.android.lab4_room

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDao) {
//взаимодействие происходит через NoteDao к NoteDatabase

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()
        // пердставляет собой Livedata (содержит список объектов базы)
        //notesDao.getAllNotes() - это метод DAO, который возвращает
        //объект, содержащий список всех записей в таблице notesTable.

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }
//insert в классе NoteRepository, которая принимает объект
// Note в качестве аргумента и вставляет его в базу данных с помощью метода insert DAO.
    //suspend -  коуртина, не блокирущая поток

    suspend fun delete(note: Note){
        notesDao.delete(note)
    }
//Этот участок кода определяет функцию delete
// которая принимает объект Note в качестве аргумента и
// удаляет соответствующую запись из базы данных с помощью метода delete из DAO-интерфейса.

    suspend fun update(note: Note){
        notesDao.update(note)
    }
}