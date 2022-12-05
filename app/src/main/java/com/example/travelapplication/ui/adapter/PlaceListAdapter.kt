package com.example.travelapplication.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelapplication.data.TourModel.ResponseObjectItem
import com.example.travelapplication.R
import com.example.travelapplication.databinding.ItemSearchPlacesBinding
import java.text.DecimalFormat

class PlaceListAdapter : ListAdapter<ResponseObjectItem, PlaceListAdapter.PlaceViewHolder>(
    NewsDiffCallback
),Filterable {

    private var list = mutableListOf<ResponseObjectItem>()
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        context = parent.context
        return PlaceViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_search_places, parent, false))
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<ResponseObjectItem>() {
            override fun areItemsTheSame(oldItem: ResponseObjectItem, newItem: ResponseObjectItem): Boolean {
                return oldItem.tOURNAME == newItem.tOURNAME
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ResponseObjectItem, newItem: ResponseObjectItem): Boolean {
                return oldItem == newItem
            }
        }
    }


    fun setData(list: MutableList<ResponseObjectItem>){
        this.list = list!!
        submitList(list)
    }

    inner class PlaceViewHolder(val binding: ItemSearchPlacesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ResponseObjectItem) = with(binding) {

            if(model.images != null && model.images.size > 0){
                Glide.with(context)
                    .load(model.images.get(0))
                    .into(imgTourImage)
            }

            //Not found exact parameter for Bonanza offer thats wh showing empty
            txtBonanzaOffer.text = context?.resources?.getString(R.string.bonanza_offer)
            if(!TextUtils.isEmpty(model.tOURCODE)){
                txtTourCode.text = model.tOURCODE
            }
            if(!TextUtils.isEmpty(model.tOURNAME)){
                txtTourName.text = model.tOURNAME
            }

            //Found airlineCode parameter that added handling for airlineCode only, remaining will be set at View.VISIBLE
            if(model.tOURMAS0 != null && model.tOURMAS0.size > 0){
                if(!TextUtils.isEmpty(model.tOURMAS0.get(0).airlineCode) && model.tOURMAS0.get(0).airlineCode != "-"){
                    imgFlight.visibility =  View.VISIBLE
                }else{
                    imgFlight.visibility =  View.GONE
                }
            }

            if(!TextUtils.isEmpty(model.nIGHTS.toString()) && model.nIGHTS.toString() != "null"){
                txtNightCount.text = model.nIGHTS.toString()
            }else{
                txtNightCount.text = "0"
            }
            if(model.tourSeriesInfo != null){
                if(!TextUtils.isEmpty(model.tourSeriesInfo.totalCountry.toString()) && model.tourSeriesInfo.totalCountry.toString() != "null"){
                    txtCountryCount.text = model.tourSeriesInfo.totalCountry.toString()
                }else{
                    txtCountryCount.text = "0"
                }
                if(!TextUtils.isEmpty(model.tourSeriesInfo.totalCity.toString()) && model.tourSeriesInfo.totalCity.toString() != "null"){
                    txtCitiesCount.text = model.tourSeriesInfo.totalCity.toString()
                }else{
                    txtCitiesCount.text = "0"
                }
            }

            if(!TextUtils.isEmpty(model.startCity) && model.startCity.toString() != "-"){
                txtJoiningPlace.text = model.startCity
            }
            if(!TextUtils.isEmpty(model.endCity) && model.endCity.toString() != "-"){
            txtLeavingPlace.text = model.endCity
        }
        if(!TextUtils.isEmpty(model.generatedCOST2DAY)){
            txtPreviousCost.visibility =  View.VISIBLE
            txtPreviousCost.text = "\u20B9"+DecimalFormat("##,##,##0").format(model.generatedCOST2DAY.toString().toInt())
            txtPreviousCost.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            txtPreviousCost.visibility =  View.GONE
        }
        if(!TextUtils.isEmpty(model.generatedNETCOST)){
            txtDiscountedCost.text =  "\u20B9"+DecimalFormat("##,##,##0").format(model.generatedNETCOST.toString().toInt())
        }else{
            txtDiscountedCost.text = "Coming Soon"
        }
        if(!TextUtils.isEmpty(model.generatedDISC2DAY)){
            txtBookingClick.visibility =  View.VISIBLE
            txtBookingClick.text = context?.resources?.getString(R.string.you_save)+DecimalFormat("##,##,##0").format(model.generatedDISC2DAY.toString().toInt())
        } else{
            txtBookingClick.visibility =  View.GONE
        }
        }
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<ResponseObjectItem>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(list)
            } else {
                for (item in list) {
                    if (item.tOURNAME.toString().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            submitList(filterResults?.values as MutableList<ResponseObjectItem>)
        }
    }

}