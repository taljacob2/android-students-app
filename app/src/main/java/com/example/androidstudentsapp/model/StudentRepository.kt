package com.example.androidstudentsapp.model

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun getAllStudents() = students.toList()
    
    fun getStudent(id: String) = students.find { it.id == id }
    
    fun addStudent(student: Student) {
        students.add(student)
    }
    
    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            students[index] = student
        }
    }
    
    fun deleteStudent(id: String) {
        students.removeIf { it.id == id }
    }
    
    fun toggleStudentCheck(id: String) {
        val student = getStudent(id)
        student?.let {
            it.isChecked = !it.isChecked
        }
    }
} 