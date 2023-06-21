package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TodoAdapter (private val todos : MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.CardTasarimHolder>(){
    class CardTasarimHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimHolder {
          return CardTasarimHolder(
              LayoutInflater.from(parent.context).inflate(R.layout.cardtasarim,parent,false)
          )
         }

    override fun getItemCount(): Int {
        return todos.size
    }
  /*addTodo fonk ogeleri listenin sonuna ekler.*/
    fun addTodo(todo : Todo){
        todos.add(todo) //'todo' ogesini listenin sonuna ekler
        notifyItemInserted(todos.size -1) /* bu ifade rv'ye yeni bir ögenin eklendiğini bildirir
         'todos.size -1 ifadesi , listenin sonuna eklenen öğenin indeksini temsil eder.'*/
    }
    fun deleteDoneTodos(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged() // rv'ye yapılan değişiklikleri bildirir. günceller
    }

    private fun toggleStrikeThrough(textViewTitle : TextView, isChecked : Boolean){
        if (isChecked){
            textViewTitle.paintFlags = textViewTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            textViewTitle.paintFlags = textViewTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: CardTasarimHolder, position: Int) {
        val textViewTitle: TextView = holder.itemView.findViewById(R.id.textViewTitle)
        val cbDone : CheckBox = holder.itemView.findViewById(R.id.cbDone)
        val curTodo = todos[position]
        holder.itemView.apply {
              textViewTitle.text = curTodo.Title
              cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(textViewTitle,curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { compoundButton, b ->
                toggleStrikeThrough(textViewTitle, b)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

}