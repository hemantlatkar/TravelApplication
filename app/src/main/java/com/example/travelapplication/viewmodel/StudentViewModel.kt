package com.example.travelapplication.viewmodel

import com.example.travelapplication.data.StudentDatabase
import com.example.travelapplication.data.repository.StudentListRepository
import com.example.travelapplication.data.StudentModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<StudentModel>>
    private val repository: StudentListRepository

    init{
        val userDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentListRepository(userDao)
        readAllData = repository.readAllData
    }

    fun getStudentRecord(studId: Int): StudentModel {
        return repository.getStudentRecord(studId)
    }

    fun addStudentRecord(studentModel: StudentModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.addStudentRecord(studentModel)
        }
    }

    fun updateStudentRecord(studentModel: StudentModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateStudentRecord(studentModel)
        }
    }

    fun deleteStudentRecord(studId: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteStudentRecord(studId)
        }
    }

    fun deleteAllImageData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllImageData()
        }
    }
}