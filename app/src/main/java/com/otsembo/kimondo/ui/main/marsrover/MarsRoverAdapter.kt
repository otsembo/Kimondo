package com.otsembo.kimondo.ui.main.marsrover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otsembo.kimondo.data.model.RoverPhoto
import com.otsembo.kimondo.databinding.MarsRoverItemBinding

class MarsRoverAdapter : ListAdapter<RoverPhoto, MarsRoverAdapter.RoverViewHolder>(RoverDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverViewHolder {
        return RoverViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RoverViewHolder, position: Int) {
        holder.bindData(currentList[position])
    }

    class RoverViewHolder(private val binding: MarsRoverItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindData(photo: RoverPhoto){
            binding.roverPhoto = photo
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RoverViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                return RoverViewHolder(
                    MarsRoverItemBinding.inflate(inflater, parent, false)
                )
            }
        }

    }

}

class RoverDiffUtil: DiffUtil.ItemCallback<RoverPhoto>(){
    override fun areItemsTheSame(oldItem: RoverPhoto, newItem: RoverPhoto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RoverPhoto, newItem: RoverPhoto): Boolean {
        return oldItem == newItem
    }
}