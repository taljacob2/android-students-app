package com.example.androidstudentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = StudentListAdapter(StudentRepository.getAllStudents()) { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra(StudentDetailsActivity.EXTRA_STUDENT_ID, student.id)
            startActivity(intent)
        }
        
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.updateStudents(StudentRepository.getAllStudents())
    }
}