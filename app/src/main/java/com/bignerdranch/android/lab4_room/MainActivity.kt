package com.bignerdranch.android.lab4_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {
    lateinit var viewModal: NoteViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)

        notesRV.layoutManager = LinearLayoutManager(this)
            //элементы будут в вертикальном положении.

        val noteRVAdapter = NoteRVAdapter(this, this, this)
            //созданный экземляр является пользовательским адаптером класса для RecyclerView, используемого для отображения заметок в вертикальном списке.
        //this 1-ссылается на данных объект 2 -на кнопку удаления 3-обработка нажатия.

        notesRV.adapter = noteRVAdapter

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)
            //provider Нужен для управлению моделью.
        //Класс AndroidViewModelFactory - это фабричный класс, который используется для создания экземпляров ViewModel, к
        // оторые имеют доступ к контексту приложения. Это полезно в случаях, когда ViewModel нуждается в доступе к
        // ресурсам или службам, которые предоставляются контекстом приложения.
        //Метод get используется для получения экземпляра NoteViewModal из ViewModelProvider.
        // Аргумент NoteViewModal::class.java указывает класс ViewModel для получения.
        //В целом, этот скрипт создает экземпляр класса NoteViewModal,
        // который имеет доступ к контексту приложения и может использоваться для управления списком заметок в приложении.



        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
            //observe метод приимает 2 аргумента:  - это LifecycleOwner, который будет получать обновления свойства allNotes,
        // а второй аргумент - это реализация интерфейса Observer, которая будет вызываться при изменении свойства allNotes.
        //Этот скрипт используется для обновления пользовательского интерфейса последним списком заметок из экземпляра NoteViewModal
        // при изменении свойства allNotes. Наблюдатель обеспечивает автоматическое и консистентное обновление


        addFAB.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }
// Затем в Intent добавляются дополнительные данные с помощью метода putExtra
//noteType - нужно редактировать существующую заметку, а не создавать новую.
//noteTitle со значением note.noteTitle, которое содержит заголовок заметки.
//noteDescription со значением note.noteDescription, которое содержит описание заметки.
//noteId со значением note.id, которое содержит уникальный идентификатор заметки.


    override fun onDeleteIconClick(note: Note) {

        viewModal.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Удалён", Toast.LENGTH_LONG).show()
    }
    //Эта строчка создает и отображает всплывающее сообщение (Toast)
// на экране устройства, уведомляющее пользователя о том, что заметка с заголовком note.noteTitle была удалена.
}