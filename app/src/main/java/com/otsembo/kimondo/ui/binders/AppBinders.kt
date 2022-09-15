package com.otsembo.kimondo.ui.binders

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.chip.Chip
import com.otsembo.kimondo.R
import com.otsembo.kimondo.data.model.NearEarthObject
import com.otsembo.kimondo.ui.main.neo.NeoAdapter

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