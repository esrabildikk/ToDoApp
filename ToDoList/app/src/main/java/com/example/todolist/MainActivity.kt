package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter : TodoAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        todoAdapter = TodoAdapter(mutableListOf())
        binding.rv.adapter = todoAdapter
        binding.rv.layoutManager = LinearLayoutManager(this)


        binding.buttonAdd.setOnClickListener {
            val todoTitle = binding.editTextEnterTodo.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.editTextEnterTodo.text.clear() //edittext'in içeriği temizlenir.
            }
        }
        binding.buttonDelete.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }
}