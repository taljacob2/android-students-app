package com.example.androidstudentsapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.example.androidstudentsapp.model.StudentRepository

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var adapter: StudentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupRecyclerView()
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.studentListRecyclerView)
        addButton = findViewById(R.id.addStudentButton)
        
        addButton.setOnClickListener {
            // TODO: Launch NewStudentActivity
        }
    }

    private fun setupRecyclerView() {
        adapter = StudentListAdapter(StudentRepository.getAllStudents()) { student ->
            // TODO: Launch StudentDetailsActivity
        }
        
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}