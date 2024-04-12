package com.bignerdranch.android.lab4_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.util.Date
import java.text.SimpleDateFormat

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var noteTitleEdt: EditText
    lateinit var noteEdt: EditText
    lateinit var saveBtn: Button


    lateinit var viewModal: NoteViewModal
    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        noteTitleEdt = findViewById(R.id.idEdtNoteName)
        noteEdt = findViewById(R.id.idEdtNoteDesc)
        saveBtn = findViewById(R.id.idBtn)


        val noteType = intent.getStringExtra("noteType")
            //Эта строчка кода получает строковое значение из входящего Intent с помощью метода getStringExtra(). Значение получается по ключу "noteType".
            //. В контексте разработки под Android Intent может использоваться для передачи данных между активностями или службами.
        val noteTitle = intent.getStringExtra("noteTitle")
        val noteDescription = intent.getStringExtra("noteDescription")
        noteID = intent.getIntExtra("noteId", -1)
        noteTitleEdt.setText(noteTitle)
        noteEdt.setText(noteDescription)



        saveBtn.setOnClickListener {
            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteEdt.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.id = noteID
                    viewModal.updateNote(updatedNote)
                    Toast.makeText(this, "Заметочка обновлена!", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    // if the string is not empty we are calling a
                    // add note method to add data to our room database.
                    viewModal.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Добавлена", Toast.LENGTH_LONG).show()
                }
            }

            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }//startActivity(Intent создает новый Intent с контекстом приложения applicationContext и
// классом целевой активности MainActivity::class.java. Затем метод startActivity() запускает эту активность.
    //Эти строчки кода запускают главную активность MainActivity и затем закрывают текущую активность.
}