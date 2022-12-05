package com.example.travelapplication.ui.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapplication.R
import com.example.travelapplication.databinding.ItemStudentRecordBinding

class RecordAdapter(private val onUpdateCallback: (studentId: Int) -> Unit)
    : ListAdapter<RecordUiModel, RecordAdapter.RecordListViewHolder>(CALLBACK) {

    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<RecordUiModel>() {
            override fun areItemsTheSame(oldItem: RecordUiModel, newItem: RecordUiModel): Boolean {
                return oldItem.id  == newItem.id
            }
            override fun areContentsTheSame(oldItem: RecordUiModel, newItem: RecordUiModel): Boolean {
                return oldItem.studentName == newItem.studentName
                        && oldItem.course == newItem.course
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordListViewHolder {
        return RecordListViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_student_record, parent, false))
    }

    override fun onBindViewHolder(holder: RecordListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RecordListViewHolder(val binding: ItemStudentRecordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: RecordUiModel) = with(binding) {
            tvStudentName.text = model.studentName
            tvCourse.text = model.course
            tvTotalMarks.text = "${model.totalMarks}/300"
            tvAvg.text = "${model.averageMarks}%"
            imgUpdate.setOnClickListener {
                onUpdateCallback(model.id)
            }
        }
    }
}

data class RecordUiModel(
        val id: Int,
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
)