package com.example.travelapplication.data.repository

import com.example.travelapplication.data.StudentDataDao
import com.example.travelapplication.data.StudentModel
import androidx.lifecycle.LiveData

class StudentListRepository (private val studentDataDao: StudentDataDao){

    val readAllData: LiveData<List<StudentModel>> = studentDataDao.readAllImageData()

    suspend fun addStudentRecord(studentModel: StudentModel){
        studentDataDao.addStudentRecord(studentModel)
    }

    fun getStudentRecord(studentId: Int): StudentModel {
        return studentDataDao.getStudentRecord(studentId)
    }

    suspend fun updateStudentRecord(studentModel: StudentModel){
        studentDataDao.updateStudentRecord(studentModel)
    }

    suspend fun deleteStudentRecord(studId: Int){
        studentDataDao.deleteStudentRecord(studId)
    }

    suspend fun deleteAllImageData(){
        studentDataDao.deleteAllImageData()
    }
}