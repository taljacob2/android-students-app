package com.example.androidstudentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.androidstudentsapp.model.StudentRepository

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var avatarImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var idTextView: TextView
    private lateinit var editButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        setupViews()
        loadStudent()
    }

    private fun setupViews() {
        avatarImageView = findViewById(R.id.studentAvatar)
        nameTextView = findViewById(R.id.studentName)
        idTextView = findViewById(R.id.studentId)
        editButton = findViewById(R.id.editButton)

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra(EXTRA_STUDENT_ID, intent.getStringExtra(EXTRA_STUDENT_ID))
            startActivity(intent)
        }
    }

    private fun loadStudent() {
        val studentId = intent.getStringExtra(EXTRA_STUDENT_ID)
        studentId?.let { id ->
            StudentRepository.getStudent(id)?.let { student ->
                nameTextView.text = student.name
                idTextView.text = student.id
                avatarImageView.setImageResource(R.drawable.default_avatar)
            }
        }
    }

    companion object {
        const val EXTRA_STUDENT_ID = "extra_student_id"
    }
} 