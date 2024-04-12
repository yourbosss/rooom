package com.bignerdranch.android.lab4_room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModal(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>
    val repository : NoteRepository

    init {
        //блок для инициализации объектов.
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
            //NoteDatabase.getDatabase(application) - вызывает статический метод
        // который возвращает экземпляр базы данных
        // NoteDatabase, используя контекст приложения application.
        //для получения доступа к базе данных.

        //get notes - получаем экземлпяр  для выполнения операций взаимодействия с
        // базой данных, таких как вставка, удаление, обновление и извлечение данных.
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote (note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    // Функция принимает объект Note в качестве аргумента и удаляет его из базы данных.
    //корутина viewModelScope.launch(Dispatchers.IO) - блокирует фоновые операции, но не главные

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }


    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}