package com.example.androidstudentsapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.androidstudentsapp.model.Student
import com.example.androidstudentsapp.model.StudentRepository

class StudentListAdapter(
    private var students: List<Student>,
    private val onStudentClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.studentAvatar)
        val name: TextView = view.findViewById(R.id.studentName)
        val id: TextView = view.findViewById(R.id.studentId)
        val checkBox: CheckBox = view.findViewById(R.id.studentCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        
        holder.name.text = student.name
        holder.id.text = student.id
        holder.checkBox.isChecked = student.isChecked
        
        holder.avatar.setImageResource(R.drawable.ic_person)
        
        holder.itemView.setOnClickListener {
            onStudentClick(student)
        }
        
        holder.checkBox.setOnClickListener {
            StudentRepository.toggleStudentCheck(student.id)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = students.size

    fun updateStudents(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
} 