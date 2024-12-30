package com.example.androidstudentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.example.androidstudentsapp.model.Student
import com.example.androidstudentsapp.model.StudentRepository

class EditStudentActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button
    private var originalId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        setupViews()
        loadStudent()
    }

    private fun setupViews() {
        nameEditText = findViewById(R.id.studentNameEditText)
        idEditText = findViewById(R.id.studentIdEditText)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.deleteButton)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            
            if (name.isNotBlank() && id.isNotBlank()) {
                StudentRepository.deleteStudent(originalId)
                StudentRepository.addStudent(Student(id, name))
                finish()
            }
        }

        deleteButton.setOnClickListener {
            StudentRepository.deleteStudent(originalId)
            finish()
        }
    }

    private fun loadStudent() {
        val studentId = intent.getStringExtra(StudentDetailsActivity.EXTRA_STUDENT_ID)
        studentId?.let { id ->
            originalId = id
            StudentRepository.getStudent(id)?.let { student ->
                nameEditText.setText(student.name)
                idEditText.setText(student.id)
            }
        }
    }
} 