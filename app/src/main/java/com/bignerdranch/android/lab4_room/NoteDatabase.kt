package com.bignerdranch.android.lab4_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
//в данном случае массив данных -note в базе жданных.
//просто какая версия базы данных
//schema - флаг, указывающий нужно ли переносить схемы в json формат (нет)

abstract class NoteDatabase : RoomDatabase() {
//анотация для обьявления базы.
// что класс NoteDatabase является базой данных,
    // которая содержит таблицы, описанные в классе Note.
    abstract fun getNotesDao(): NotesDao
//возвращает объект, реализующий интерфейс NotesDao,
    // который содержит методы для работы с таблицей "notesTable" в базе данных.
    companion object {
        //бъект компаньона - это объект, который принадлежит классу,
        // но не связан с экземпляром класса.
        @Volatile
        //значит что переменные могут обновляться асинхронно из разных потоках.
        //и значения переменных могут изменится в лююой момент
        private var INSTANCE: NoteDatabase? = null
            //Эта переменная будет использоваться для хранения
    // единственного экземпляра объекта NoteDatabase, который будет создан в приложении.

        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                //проверка существует ли экземпляр базы данных
                //если есть- возращается, если нет -создаеется через Room
                //synchronized- только один поток может возращать значенич
                val instance = Room.databaseBuilder(
                    //builder - принимает контекст, классы базы и имя.
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}