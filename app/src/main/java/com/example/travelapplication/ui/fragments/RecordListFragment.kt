package com.example.travelapplication.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.travelapplication.R
import com.example.travelapplication.databinding.FragmentListBinding
import com.example.travelapplication.ui.fragments.list.RecordAdapter
import com.example.travelapplication.ui.fragments.list.RecordUiModel
import com.example.travelapplication.viewmodel.StudentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecordListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[StudentViewModel::class.java] }

    private val referralFormAdapter: RecordAdapter by lazy { RecordAdapter( onUpdateCallback = { id  -> onUpdateScreen(id) }) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListBinding.inflate(layoutInflater)
        binding.recyclerView.adapter = referralFormAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.swiperefresh.setOnRefreshListener(OnRefreshListener {
            binding.swiperefresh.isRefreshing =  true
            observeViewModelData()
        })
        setHasOptionsMenu(true)
        observeViewModelData()
        return binding.root
    }

    private fun observeViewModelData() {
        with(viewModel) {
            readAllData.observe(viewLifecycleOwner, Observer {
                try{
                    val recordList: ArrayList<RecordUiModel> = ArrayList()
                    it?.forEach {model ->
                        recordList.add(
                            RecordUiModel(model.id!!,model.studentName,model.course,
                            model.subjectName_1,model.subjectMarks_1,model.subjectName_2,model.subjectMarks_2,
                            model.subjectName_3,model.subjectMarks_3,model.totalMarks,model.averageMarks)
                        )
                    }
                    binding.swiperefresh.isRefreshing =  false
                    referralFormAdapter.submitList(recordList)
                    if(recordList.size > 0){
                        binding.lblNoRecord.visibility =  View.GONE
                    }else{
                        binding.lblNoRecord.visibility =  View.VISIBLE
                    }
                }catch(e : Exception){
                    binding.swiperefresh.isRefreshing =  false
                }
            })
        }
    }

    private fun onUpdateScreen(studentId : Int){
        CoroutineScope(Dispatchers.IO).launch {
            val studentRecord = viewModel.getStudentRecord(studentId)
            val action = RecordListFragmentDirections.actionListFragmentToUpdateFragment(studentRecord)
            withContext (Dispatchers.Main) {
                findNavController().navigate(action)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.task_two_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_add){
            val action = RecordListFragmentDirections.actionListFragmentToAddFragment()
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

    //Custom view for alert dialog
    private fun deleteAllRecord() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Delete all?")
        builder.setMessage("Are you sure you want to clear all student record?")
        builder.setPositiveButton("Yes"){_,_->
            viewModel.deleteAllImageData()
            Toast.makeText(requireActivity(),"All record deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.create().show()
    }
}