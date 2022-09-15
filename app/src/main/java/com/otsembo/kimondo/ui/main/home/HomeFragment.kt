package com.otsembo.kimondo.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.otsembo.kimondo.data.db.AppDatabase
import com.otsembo.kimondo.data.db.dao.ApodDao
import com.otsembo.kimondo.data.network.RetrofitUtils
import com.otsembo.kimondo.data.repository.APODRepository
import com.otsembo.kimondo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var dao: ApodDao
    private lateinit var viewModel: HomeVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initVM()
        return binding.root
    }

    private fun initVM(){
        dao = AppDatabase.getDB(requireContext().applicationContext).apodDao()
        viewModel = HomeVM(APODRepository(nasaService = RetrofitUtils.nasaService, dao = dao))
        binding.viewModel = viewModel
    }

}