package com.example.search.resultscreen

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.data.model.VideoModel
import com.example.domain.search.SearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val searchResultUseCase : SearchResultUseCase
) : BaseViewModel<ViewState, SearchResultEvent>(){
    private val _resultVideoList = MutableStateFlow<List<VideoModel>>(arrayListOf())
    val resultVideosList = _resultVideoList.asStateFlow()

    override fun onTriggerEvent(event: SearchResultEvent) {
    }

    init {
        // không làm gì cả
    }

    fun getResultVideoList(query: String) : StateFlow<List<VideoModel>> {
        fetchResultVideoList(query)
        return resultVideosList
    }

    private fun fetchResultVideoList(query: String) {
        //lấy video tương ứng với search query về
        viewModelScope.launch {
            searchResultUseCase(query).collect {
                    videoList ->
                if (videoList.isNotEmpty()) {
                    // There are VideoModel objects in the list
                    Log.d("TAG", "Found videos: ${videoList.size}")
                } else {
                    // The list is empty, no VideoModel objects found
                    Log.d("TAG", "No videos found")
                }
                _resultVideoList.value = videoList
            }
        }
    }
}