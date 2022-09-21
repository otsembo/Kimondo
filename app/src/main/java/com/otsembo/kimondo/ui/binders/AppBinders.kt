package com.otsembo.kimondo.ui.binders

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.otsembo.kimondo.R
import com.otsembo.kimondo.data.model.NearEarthObject
import com.otsembo.kimondo.data.model.RoverPhoto
import com.otsembo.kimondo.data.model.SearchData
import com.otsembo.kimondo.ui.main.marsrover.MarsRoverAdapter
import com.otsembo.kimondo.ui.main.neo.NeoAdapter
import com.otsembo.kimondo.ui.main.search.SearchDataAdapter

const val TAG = "APP-BINDERS"

@BindingAdapter("kimondoImage")
fun ImageView.setKimondoImage(imageUrl: String?){
    this.load(imageUrl)
}

@BindingAdapter("hazardChip")
fun Chip.setHazardStatus(isHazard: Boolean){
    if(isHazard){
        this.setChipBackgroundColorResource(android.R.color.holo_red_dark)
        this.chipIcon =  AppCompatResources.getDrawable(this.context, R.drawable.ic_hazardous)
        this.text = this.context.resources.getString(R.string.hazardous)
    }else{
        this.setChipBackgroundColorResource(android.R.color.holo_green_dark)
        this.chipIcon =  AppCompatResources.getDrawable(this.context, R.drawable.ic_non_hazard)
        this.text = this.context.resources.getString(R.string.non_hazardous)
    }
}

@BindingAdapter("magnitude")
fun TextView.setMagnitude(magnitude: Double){
    this.text = this.context.resources.getString(R.string.absolute_magnitude, magnitude)
}

@BindingAdapter("neoList")
fun RecyclerView.setNeoList(neos: List<NearEarthObject>?){
    (this.adapter as NeoAdapter).submitList(neos)
}

@BindingAdapter("roverPhotoList")
fun RecyclerView.setRoverPhotoList(photos: List<RoverPhoto>?){
    (this.adapter as MarsRoverAdapter).submitList(photos)
}

@BindingAdapter("searchList")
fun RecyclerView.setSearchList(searchDataList: List<SearchData>?){
    (this.adapter as SearchDataAdapter).submitList(searchDataList)
}

@BindingAdapter("isLoading")
fun LinearProgressIndicator.setLoading(loading: Boolean?){
    loading?.let { currentlyLoading ->
        this.visibility = if(currentlyLoading) View.VISIBLE else View.GONE
    }
}