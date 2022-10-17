package com.otsembo.kimondo.ui.main.neo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.kimondo.common.AppResource
import com.otsembo.kimondo.data.model.NearEarthObject
import com.otsembo.kimondo.data.repository.NEOWSRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.format
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class NeoVM(private val neowsRepository: NEOWSRepository) : ViewModel() {

    val neos = neowsRepository.displayNeoWs()
    private val _neos: MutableStateFlow<AppResource<List<NearEarthObject>>> = MutableStateFlow(AppResource.Idle())
    val near_earth_objects: StateFlow<AppResource<List<NearEarthObject>>> = _neos


    init {
        viewModelScope.launch{
            _neos.emit(AppResource.Loading())
            try{
                val dates = getDates()
                neowsRepository.retrieveNearEarthObjects(start_date = dates.first, end_date = dates.second)
                _neos.emit(AppResource.Success(data = emptyList()))
            }catch (e: Exception){
                e.localizedMessage?.let {
                    _neos.emit(AppResource.Error(message = it))
                }
            }

        }
    }

    private fun getDates() : Pair<String, String>{
        val fomatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendarToday = Calendar.getInstance()
        val calendarOneWeekAgo = Calendar.getInstance()
        calendarOneWeekAgo.add(Calendar.DATE, -7)
        return Pair(first = fomatter.format(calendarToday.time), second = fomatter.format(calendarOneWeekAgo.time))
    }

}