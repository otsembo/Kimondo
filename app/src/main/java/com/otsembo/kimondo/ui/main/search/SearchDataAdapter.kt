package com.otsembo.kimondo.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otsembo.kimondo.data.model.SearchData
import com.otsembo.kimondo.databinding.SearchItemBinding

class SearchDataAdapter  : ListAdapter<SearchData, SearchDataAdapter.SearchHolder>(SearchDataDiff()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bindData(currentList[position])
    }

    class SearchHolder(private val binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(searchData: SearchData){
            binding.searchData = searchData
            binding.executePendingBindings()
        }

        companion object {
            fun from(container: ViewGroup): SearchHolder{
                val inflater = LayoutInflater.from(container.context)
                return SearchHolder(
                    SearchItemBinding.inflate(inflater, container, false)
                )
            }
        }

    }
}

class SearchDataDiff : DiffUtil.ItemCallback<SearchData>(){
    override fun areItemsTheSame(oldItem: SearchData, newItem: SearchData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchData, newItem: SearchData): Boolean {
        return oldItem == newItem
    }
}