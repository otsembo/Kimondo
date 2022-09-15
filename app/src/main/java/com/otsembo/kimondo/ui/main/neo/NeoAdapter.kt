package com.otsembo.kimondo.ui.main.neo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otsembo.kimondo.data.model.NearEarthObject
import com.otsembo.kimondo.databinding.NeoItemBinding

class NeoAdapter : ListAdapter<NearEarthObject, NeoAdapter.NeoViewHolder>(NeoDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeoViewHolder {
        return NeoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NeoViewHolder, position: Int) {
        holder.bindNeos(currentList[position])
    }

    class NeoViewHolder(private val binding: NeoItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindNeos(neo: NearEarthObject){
            binding.neo = neo
            binding.executePendingBindings()
        }

        companion object{
            fun from(container: ViewGroup): NeoViewHolder{
                val inflater = LayoutInflater.from(container.context)
                return NeoViewHolder(NeoItemBinding.inflate(inflater, container, false))
            }
        }

    }


    class NeoDiffCallBack: DiffUtil.ItemCallback<NearEarthObject>(){
        override fun areItemsTheSame(oldItem: NearEarthObject, newItem: NearEarthObject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NearEarthObject, newItem: NearEarthObject): Boolean {
            return oldItem == newItem
        }
    }

}

