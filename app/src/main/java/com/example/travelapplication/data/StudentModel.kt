package com.example.travelapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "student_table", indices = [Index(value = ["studentName"], unique = true)])
data class StudentModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val studentName: String,
    val course: String,
    val subjectName_1: String,
    val subjectMarks_1: Int,
    val subjectName_2: String,
    val subjectMarks_2: Int,
    val subjectName_3: String,
    val subjectMarks_3: Int,
    val totalMarks: Int,
    val averageMarks: Float
): Parcelable