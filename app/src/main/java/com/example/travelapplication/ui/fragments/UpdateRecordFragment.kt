package com.example.travelapplication.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.travelapplication.data.StudentModel
import com.example.travelapplication.R
import com.example.travelapplication.databinding.FragmentUpdateBinding
import com.example.travelapplication.viewmodel.StudentViewModel
import com.example.travelapplication.ui.fragments.UpdateRecordFragmentArgs
import java.text.DecimalFormat

class UpdateRecordFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding: FragmentUpdateBinding
        get() = _binding!!

    private val args by navArgs<UpdateRecordFragmentArgs>()
    private val viewModel by lazy { ViewModelProvider(this)[StudentViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUpdateBinding.inflate(layoutInflater)

        binding.etName.setText(args.studentData.studentName)
        val coursesList = resources.getStringArray(R.array.CourseName)
        val adapter = ArrayAdapter(requireActivity(),R.layout.spinner_drop_down,coursesList)
        binding.spCourse.adapter = adapter
        binding.spCourse.setSelection(adapter.getPosition(args.studentData.course))
        binding.spCourse.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if(args.studentData.course != coursesList[position] )
                        courseRelatedQuestions(coursesList[position])
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        binding.tvOne.text = args.studentData.subjectName_1
        binding.etOne.setText(args.studentData.subjectMarks_1.toString())
        binding.tvTwo.text = args.studentData.subjectName_2
        binding.etTwo.setText(args.studentData.subjectMarks_2.toString())
        binding.tvThree.text = args.studentData.subjectName_3
        binding.etThree.setText(args.studentData.subjectMarks_3.toString())
        binding.tvMarks.text = "Total Marks : ${args.studentData.totalMarks}"
        binding.tvAvg.text = "Average : ${args.studentData.averageMarks}"

        binding.imgDropdown.setOnClickListener { binding.spCourse.performClick() }

        binding.etOne.afterTextChanged { calculateMarks() }
        binding.etTwo.afterTextChanged { calculateMarks() }
        binding.etThree.afterTextChanged { calculateMarks() }

        binding.btnDelete.setOnClickListener { deleteUser() }
        binding.btnUpdate.setOnClickListener { insertRecord() }
        return binding.root
    }

    fun courseRelatedQuestions(selectedCourse : String){
        val translateAnimation = TranslateAnimation(0f, 0f, 1200f, 0f)
        translateAnimation.interpolator = LinearInterpolator()
        translateAnimation.duration = 400
        translateAnimation.fillAfter = true
        binding.constQuestions.startAnimation(translateAnimation)
        when(selectedCourse){
            "CSE" -> {
                binding.tvOne.text = "C language"
                binding.tvTwo.text = "Java"
                binding.tvThree.text = "Data structure"
                binding.etOne.setText("")
                binding.etTwo.setText("")
                binding.etThree.setText("")
                binding.tvMarks.text = "Total Marks : "
                binding.tvAvg.text = "Average : "
            }
            "ECE" -> {
                binding.tvOne.text = "Digital electronics"
                binding.tvTwo.text = "Mathematics"
                binding.tvThree.text = "Microprocessor"
                binding.etOne.setText("")
                binding.etTwo.setText("")
                binding.etThree.setText("")
                binding.tvMarks.text = "Total Marks : "
                binding.tvAvg.text = "Average : "
            }
            "Mech" -> {
                binding.tvOne.text = "Nanotechnology"
                binding.tvTwo.text = "Biometrics"
                binding.tvThree.text = "Acoustics"
                binding.etOne.setText("")
                binding.etTwo.setText("")
                binding.etThree.setText("")
                binding.tvMarks.text = "Total Marks : "
                binding.tvAvg.text = "Average : "
            }
            "Civil" -> {
                binding.tvOne.text = "Material science"
                binding.tvTwo.text = "Construction Engineering"
                binding.tvThree.text = "Hydraulic science"
                binding.etOne.setText("")
                binding.etTwo.setText("")
                binding.etThree.setText("")
                binding.tvMarks.text = "Total Marks : "
                binding.tvAvg.text = "Average : "
            }
        }
    }

    private fun insertRecord(){
        if(validation()){
            val studentName = binding.etName.text.toString()
            val courseName = binding.spCourse.selectedItem.toString()

            val subOneName =  binding.tvOne.text.toString()
            val subOneMarks =  binding.etOne.text.toString()
            val subTwoName =  binding.tvTwo.text.toString()
            val subTwoMarks =  binding.etTwo.text.toString()
            val subThreeName =  binding.tvThree.text.toString()
            val subThreeMarks =  binding.etThree.text.toString()

            if(binding.etOne.text.toString().toInt() <= 100 && binding.etTwo.text.toString().toInt() <= 100 && binding.etThree.text.toString().toInt() <= 100){
                val totalMarks =  (binding.etOne.text.toString().toInt() + binding.etTwo.text.toString().toInt() + binding.etThree.text.toString().toInt())
                val avgMarks = (totalMarks.toFloat() / 3)
                val decFormat = DecimalFormat("#.##")
                val updatedStudentModel =  StudentModel(args.studentData.id, studentName,courseName,
                    subOneName,subOneMarks.toInt(),subTwoName, subTwoMarks.toInt(),subThreeName,subThreeMarks.toInt(),
                    totalMarks,decFormat.format(avgMarks).toFloat())
                viewModel.updateStudentRecord(updatedStudentModel)
                Toast.makeText(requireActivity(),"Updated ${args.studentData.studentName} successfully" ,Toast.LENGTH_LONG).show()
                findNavController().popBackStack()

            }else{
                Toast.makeText(requireActivity(), "Marks should be less than or equal 100", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validation(): Boolean {
        if (TextUtils.isEmpty(binding.etName.text.toString())) {
            binding.etName.error = "Enter student name"
            return false
        }
        if (binding.spCourse.selectedItemPosition == -1) {
            Toast.makeText(requireActivity(), "Select course", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(binding.etOne.text.toString())) {
            binding.etOne.error = "Enter ${binding.tvOne.text} marks"
            return false
        }
        if (TextUtils.isEmpty(binding.etTwo.text.toString())) {
            binding.etTwo.error = "Enter ${binding.tvTwo.text} marks"
            return false
        }
        if (TextUtils.isEmpty(binding.etThree.text.toString())) {
            binding.etThree.error = "Enter ${binding.tvThree.text} marks"
            return false
        }
        return true
    }

    private fun calculateMarks() {
        if (TextUtils.isEmpty(binding.etOne.text.toString())) return
        if (TextUtils.isEmpty(binding.etTwo.text.toString())) return
        if (TextUtils.isEmpty(binding.etThree.text.toString())) return
        
        if(binding.etOne.text.toString().toInt() <= 100 && binding.etTwo.text.toString().toInt() <= 100 && binding.etThree.text.toString().toInt() <= 100){
            val totalMarks =  (binding.etOne.text.toString().toInt() + binding.etTwo.text.toString().toInt() + binding.etThree.text.toString().toInt())
            val avgMarks = (totalMarks.toFloat() / 3)
            val decFormat = DecimalFormat("#.##")
            binding.tvMarks.text = "Total Marks : ${totalMarks}"
            binding.tvAvg.text = "Average : ${decFormat.format(avgMarks)}%"
        }
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireActivity())
        builder.setPositiveButton("Yes"){_,_-> args.studentData.id.let {
                viewModel.deleteStudentRecord(it!!)
            }
            Toast.makeText(requireActivity(),"Deleted record",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete ${args.studentData.studentName} data?")
        builder.setMessage("Are you sure you want to delete record of ${args.studentData.studentName}?")
        builder.create().show()
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }

}