package com.example.androidstudentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.example.androidstudentsapp.model.Student
import com.example.androidstudentsapp.model.StudentRepository

class NewStudentActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        setupViews()
    }

    private fun setupViews() {
        nameEditText = findViewById(R.id.studentNameEditText)
        idEditText = findViewById(R.id.studentIdEditText)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            
            if (name.isNotBlank() && id.isNotBlank()) {
                StudentRepository.addStudent(Student(id, name))
                finish()
            }
        }
    }
} 