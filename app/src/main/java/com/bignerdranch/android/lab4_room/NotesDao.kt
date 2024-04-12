package com.bignerdranch.android.lab4_room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
//аннотация для объявления интерфейса,
//который будет заниматься манипулированием данными базы данных.
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // аннотация, которая указывает, что метод insert используется для вставки объекта Note в таблицу "notesTable".
    // Если объект с
    // таким же значением первичного ключа уже существует в таблице, он будет проигнорирован.
    //это полезно, чтобы не дублировать данные по первичному ключу.

    suspend fun insert(note :Note)
    //suspend -отложенный fun -метод insert -вставка данных
    //вставка данных будет выполняться в корутине без блокировки потока.

    @Delete
    //анотация для удаления какого-либо объекта.
    suspend fun delete(note: Note)
//метод используется для удаления объекта Note из таблицы "notesTable" в базе данных.

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
//@Query - аннотация, которая позволяет выполнить SQL-запрос в методах DAO-интерфейса.
    //SQL-запрос для извлечения всех записей из таблицы "notesTable" в базе данных.
    // выбирает все столбцы (*) из таблицы
    // "notesTable" и упорядочивает результаты по возрастанию значения столбца id.
//метод getAllNotes возвращает объект LiveData, который содержит список объектов Note. Это позволяет получать список всех записей из таблицы
    // "notesTable" в базе данных и автоматически обновлять этот список при изменениях.

    @Update
    suspend fun update(note: Note)
//анотация, указывает что должно происходит обно-ие обьекта note.
}