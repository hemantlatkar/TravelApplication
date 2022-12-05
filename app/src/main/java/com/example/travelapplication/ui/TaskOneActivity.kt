package com.example.travelapplication.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapplication.*
import com.example.travelapplication.data.TourModel.ResponseObjectItem
import com.example.travelapplication.databinding.ActivityTaskOneBinding
import com.example.travelapplication.ui.adapter.PlaceListAdapter
import com.example.travelapplication.util.ViewModelFactory
import com.example.travelapplication.viewmodel.PlacesViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class TaskOneActivity : AppCompatActivity() {

    private lateinit var mViewModel: PlacesViewModel
    private val mPlaceListAdapter: PlaceListAdapter by lazy { PlaceListAdapter() }

    private lateinit var mSearchItemList: PagedList<ResponseObjectItem>
    private lateinit var searchView: SearchView

    private var _binding: ActivityTaskOneBinding? = null
    private val binding: ActivityTaskOneBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_task_one)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlacesViewModel::class.java)

        callSearchPlaceAPI(DEFAULT_SEARCH_TOUR_SERIES, DEFAULT_SEARCH_ZONE, DEFAULT_SEARCH_TOUR_ID)
        binding.placeRecyclerView.apply {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    binding.btnScrollUp.visibility = if (dy > 0 || dy < 0) View.INVISIBLE else View.VISIBLE
                }
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        binding.btnScrollUp.visibility = View.VISIBLE
                    }
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })
            adapter = mPlaceListAdapter
            layoutManager = LinearLayoutManager(this@TaskOneActivity, RecyclerView.VERTICAL, false)
        }
        binding.btnScrollUp.setOnClickListener { binding.placeRecyclerView.smoothScrollToPosition(0); }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.task_one_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(query: String): Boolean {
                mPlaceListAdapter.filter.filter(query)
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                hideKeyboard(this@TaskOneActivity)
                mPlaceListAdapter.filter.filter(query)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun callSearchPlaceAPI(tourSeries: String, zone: String, tourID: String) {
        binding.progressbar.visibility =  View.VISIBLE
        mViewModel.getPlacesList(tourSeries, zone,tourID).observe(this, Observer {
            if (it.size > 0) {
                binding.progressbar.visibility =  View.GONE
                mPlaceListAdapter?.setData(it)
            } else {
                resetList()
                showErrorDialog(this, resources.getString(R.string.place_not_found))
            }
        })
    }

    private fun resetList() {
        hideKeyboard(this)
        binding.progressbar.visibility =  View.GONE
        mPlaceListAdapter?.setData(ArrayList())
        mPlaceListAdapter?.notifyDataSetChanged()
    }
}
