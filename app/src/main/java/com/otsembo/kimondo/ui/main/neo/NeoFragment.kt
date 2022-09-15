package com.otsembo.kimondo.ui.main.neo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.otsembo.kimondo.data.db.AppDatabase
import com.otsembo.kimondo.data.network.RetrofitUtils
import com.otsembo.kimondo.data.repository.NEOWSRepository
import com.otsembo.kimondo.databinding.FragmentNeoListBinding

class NeoFragment : Fragment() {

    private lateinit var binding: FragmentNeoListBinding
    private lateinit var appDb: AppDatabase
    private lateinit var repository: NEOWSRepository
    private lateinit var viewModel: NeoVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNeoListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        // initialize adapter before fetching from internet
        binding.neoList.adapter = NeoAdapter()
        initVM()
        return binding.root
    }

    private fun initVM() {
        appDb = AppDatabase.getDB(requireContext().applicationContext)
        repository = NEOWSRepository(dao = appDb.neoDao(), nasaService = RetrofitUtils.nasaService)
        viewModel = NeoVM(repository)
        binding.viewmodel = viewModel
    }

}