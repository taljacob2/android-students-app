package com.example.androidstudentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.androidstudentsapp.model.StudentRepository

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var avatarImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var idTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var checkedCheckBox: CheckBox
    private lateinit var editButton: Button

    private var currentStudentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        setupViews()
        loadStudent()
    }

    private fun setupViews() {
        avatarImageView = findViewById(R.id.studentAvatar)
        nameTextView = findViewById(R.id.nameTextView)
        idTextView = findViewById(R.id.idTextView)
        phoneTextView = findViewById(R.id.phoneTextView)
        addressTextView = findViewById(R.id.addressTextView)
        checkedCheckBox = findViewById(R.id.checkedCheckBox)
        editButton = findViewById(R.id.editButton)

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra(EXTRA_STUDENT_ID, currentStudentId)
            startActivityForResult(intent, EDIT_STUDENT_REQUEST)
        }
    }

    private fun loadStudent() {
        val studentId = currentStudentId ?: intent.getStringExtra(EXTRA_STUDENT_ID)
        studentId?.let { id ->
            currentStudentId = id
            StudentRepository.getStudent(id)?.let { student ->
                nameTextView.text = student.name
                idTextView.text = student.id
                phoneTextView.text = student.phone
                addressTextView.text = student.address
                checkedCheckBox.isChecked = student.isChecked
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_STUDENT_REQUEST && resultCode == RESULT_OK) {
            // Update the current student ID with the new one
            currentStudentId = data?.getStringExtra(EXTRA_STUDENT_ID)
            loadStudent()
        }
    }

    override fun onResume() {
        super.onResume()
        loadStudent()
    }

    companion object {
        const val EXTRA_STUDENT_ID = "extra_student_id"
        private const val EDIT_STUDENT_REQUEST = 1
    }
} 