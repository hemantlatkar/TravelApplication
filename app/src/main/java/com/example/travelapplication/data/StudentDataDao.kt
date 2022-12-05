package com.example.travelapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStudentRecord(studentModel: StudentModel)

    @Query("SELECT * FROM student_table ORDER BY id ASC")
    fun readAllImageData(): LiveData<List<StudentModel>>

    @Query("SELECT * FROM student_table WHERE id = :studId")
    fun getStudentRecord(studId: Int): StudentModel

    @Update
    fun updateStudentRecord(studentModel: StudentModel)

    @Query("DELETE FROM student_table WHERE id = :studId")
    fun deleteStudentRecord(studId: Int)

    @Query("DELETE FROM student_table")
    fun deleteAllImageData()
}