package com.bignerdranch.android.lab4_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
//Entity - это класс/сущность, который представляет собой
// таблицу в базе данных SQLite в библиотеке Room для Android.
//указывает, что следующий класс Note является сущностью базы данных.
//Entity класс используется для создания таблицы.

class Note(@ColumnInfo(name = "title")val noteTitle :String,
           @ColumnInfo(name = "description")val noteDescription :String,
           @ColumnInfo(name = "timestamp")val timeStamp :String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}
//@ColumnInfo - аннотация используется для
// предоставления информации о сопоставлении свойств с столбцами базы данными (имена).

//Каждая сущность должна иметь хотя бы одно поле с пометкой PrimaryKey (первичный ключ).
// Атрибут autoGenerate  что означает, что база данных автоматически генерирует
// уникальное значение для свойства id, когда вставляется новая строка в таблицу "notesTable".

