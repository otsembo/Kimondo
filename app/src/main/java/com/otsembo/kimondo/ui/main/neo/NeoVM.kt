package com.otsembo.kimondo.ui.main.neo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.kimondo.data.repository.NEOWSRepository
import kotlinx.coroutines.launch
import okhttp3.internal.format
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class NeoVM(private val neowsRepository: NEOWSRepository) : ViewModel() {

    val neos = neowsRepository.displayNeoWs()

    init {
        viewModelScope.launch{
            val dates = getDates()
            neowsRepository.retrieveNearEarthObjects(start_date = dates.first, end_date = dates.second)
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