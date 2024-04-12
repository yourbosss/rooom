package com.bignerdranch.android.lab4_room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(val context: Context,
                    val noteClickDeleteInterface: NoteClickDeleteInterface,
                    val noteClickInterface: NoteClickInterface) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {
    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }
    //Этот скрипт определяет внутренний класс ViewHolder для обработки элементов списка в RecyclerView.
    // Этот класс наследуется от RecyclerView.ViewHolder и принимает View в качестве аргумента конструктора.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.dateTV.setText("Последнее изменение : " + allNotes.get(position).timeStamp)
        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }
        //Этот скрипт определяет метод onBindViewHolder для RecyclerView,
        // который используется для связывания данных с каждым элементом списка. Этот метод принимает два аргумента: holder и position.
        //holder представляет собой объект ViewHolder, который был создан ранее и используется для доступа к элементам пользовательского интерфейса в элементе списка.
        //position представляет собой позицию текущего элемента списка в данных.
        //Внутри метода onBindViewHolder устанавливается текст для noteTV и dateTV с помощью данных из allNotes по позиции текущего элемента списка.

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
//Этот скрипт определяет метод getItemCount для RecyclerView, который возвращает количество элементов в данных, которые будут отображены в RecyclerView.
//В данном случае, метод getItemCount возвращает размер списка allNotes с помощью оператора size.


    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
    //notifyDataSetChanged() - это метод, который используется для уведомления RecyclerView о том, что данные изменились и необходимо перерисовать список.
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
    fun onNoteClick(note: Note)
}