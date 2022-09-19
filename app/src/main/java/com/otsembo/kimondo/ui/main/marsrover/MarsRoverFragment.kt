package com.otsembo.kimondo.ui.main.marsrover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.otsembo.kimondo.data.db.AppDatabase
import com.otsembo.kimondo.data.db.dao.RoverPhotoDao
import com.otsembo.kimondo.data.network.RetrofitUtils
import com.otsembo.kimondo.data.repository.MarsPhotosRepository
import com.otsembo.kimondo.databinding.FragmentMarsRoverListBinding

class MarsRoverFragment : Fragment() {

    private lateinit var binding: FragmentMarsRoverListBinding
    private lateinit var repository: MarsPhotosRepository
    private lateinit var dao: RoverPhotoDao
    private lateinit var viewmodel: MarsRoverVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMarsRoverListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initVM()
        return binding.root
    }

    private fun initVM(){
        dao = AppDatabase.getDB(context =  requireContext().applicationContext).roverPhotoDao()
        repository = MarsPhotosRepository(nasaService = RetrofitUtils.nasaService, dao = dao)
        viewmodel = MarsRoverVM(marsPhotosRepository = repository)
        binding.viewmodel = viewmodel
        binding.roverPhotoList.adapter = MarsRoverAdapter()
    }


}