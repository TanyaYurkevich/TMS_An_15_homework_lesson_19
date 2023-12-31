package com.example.tms_an_15_homework_lesson_19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.example.tms_an_15_homework_lesson_19.adapter.NoteViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        // recyclerView.layoutManager = LinearLayoutManager(this) - в xml есть
        val adapter = NoteViewAdapter()
        recyclerView.adapter = adapter

        val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val newNote = it.data?.getParcelableExtra<ItemNote>(CreatingNote.NEW_NOTE)
            if (newNote != null) {
                adapter.addNoteList(newNote)
            }
        }
        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener {
            activityResultLauncher.launch(Intent(this, CreatingNote::class.java))
        }
    }
}