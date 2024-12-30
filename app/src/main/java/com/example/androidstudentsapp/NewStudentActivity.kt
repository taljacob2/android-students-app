package com.example.androidstudentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.androidstudentsapp.model.Student
import com.example.androidstudentsapp.model.StudentRepository

class NewStudentActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var checkedCheckBox: CheckBox
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        setupViews()
    }

    private fun setupViews() {
        nameEditText = findViewById(R.id.nameEditText)
        idEditText = findViewById(R.id.idEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        addressEditText = findViewById(R.id.addressEditText)
        checkedCheckBox = findViewById(R.id.checkedCheckBox)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()
            
            if (name.isNotBlank() && id.isNotBlank()) {
                StudentRepository.addStudent(Student(
                    id = id,
                    name = name,
                    phone = phone,
                    address = address,
                    isChecked = checkedCheckBox.isChecked
                ))
                finish()
            }
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
} 