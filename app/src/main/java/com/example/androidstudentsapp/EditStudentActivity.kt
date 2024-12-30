package com.example.androidstudentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.androidstudentsapp.model.Student
import com.example.androidstudentsapp.model.StudentRepository
import android.content.Intent

class EditStudentActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var checkedCheckBox: CheckBox
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button
    private lateinit var cancelButton: Button
    private var originalId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        setupViews()
        loadStudent()
    }

    private fun setupViews() {
        nameEditText = findViewById(R.id.nameEditText)
        idEditText = findViewById(R.id.idEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        addressEditText = findViewById(R.id.addressEditText)
        checkedCheckBox = findViewById(R.id.checkedCheckBox)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.deleteButton)
        cancelButton = findViewById(R.id.cancelButton)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()
            
            if (name.isNotBlank() && id.isNotBlank()) {
                StudentRepository.deleteStudent(originalId)
                StudentRepository.addStudent(Student(
                    id = id,
                    name = name,
                    phone = phone,
                    address = address,
                    isChecked = checkedCheckBox.isChecked
                ))
                val resultIntent = Intent()
                resultIntent.putExtra(StudentDetailsActivity.EXTRA_STUDENT_ID, id)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        deleteButton.setOnClickListener {
            StudentRepository.deleteStudent(originalId)
            finish()
        }

        cancelButton.setOnClickListener {
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
                phoneEditText.setText(student.phone)
                addressEditText.setText(student.address)
                checkedCheckBox.isChecked = student.isChecked
            }
        }
    }
} 