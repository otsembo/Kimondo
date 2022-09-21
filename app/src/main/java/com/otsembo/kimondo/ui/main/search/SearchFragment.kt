package com.otsembo.kimondo.ui.main.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.otsembo.kimondo.R
import com.otsembo.kimondo.data.db.AppDatabase
import com.otsembo.kimondo.data.db.dao.SearchDataDao
import com.otsembo.kimondo.data.network.RetrofitUtils
import com.otsembo.kimondo.data.repository.SearchRepository
import com.otsembo.kimondo.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchVM
    private lateinit var searchRepository: SearchRepository
    private lateinit var appDb: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchList.adapter = SearchDataAdapter()
        setHasOptionsMenu(true)
        initVM()
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchQuery ->
                    viewModel.query.value = searchQuery
                    viewModel.searchNasa()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }


    private fun initVM(){
        appDb = AppDatabase.getDB(requireContext().applicationContext)
        searchRepository = SearchRepository(searchDataDao = appDb.searchDataDao(), albumDao = appDb.albumDao(), keywordsDao = appDb.keywordDao(), nasaImageService = RetrofitUtils.nasaImageService)
        viewModel = SearchVM(searchRepository)
        binding.viewModel = viewModel
    }


}