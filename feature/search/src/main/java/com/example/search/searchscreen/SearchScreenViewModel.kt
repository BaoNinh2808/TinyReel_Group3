package com.example.search.searchscreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.data.model.VideoModel
import com.example.domain.search.SearchUseCase
import com.example.search.resultscreen.SearchResultEvent
import com.example.search.resultscreen.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchUseCase : SearchUseCase,
) : BaseViewModel<ViewState, SearchResultEvent>(){
    private val _oldSearchQuery = MutableStateFlow<List<String>>(arrayListOf())

    private val _hotSearchQuery = MutableStateFlow<List<String>>(arrayListOf())

    val oldSearchQuery = _oldSearchQuery.asStateFlow()
    val hotSearchQuery = _hotSearchQuery.asStateFlow()
    override fun onTriggerEvent(event: SearchResultEvent) {
    }

    init {
        fetchHotSearchQuery()
        fetchOldSearchQuery()
    }

    fun updateSearchQuery(
        userId : Int,
        query : String
    ){
        searchUseCase.updateSearchQuery(userId, query)
        fetchOldSearchQuery()
    }

    private fun fetchHotSearchQuery() {
        viewModelScope.launch {
            searchUseCase(isOldSearch = false).collect {
                _hotSearchQuery.value = it
            }
        }
    }

    private fun fetchOldSearchQuery() {
        viewModelScope.launch {
            searchUseCase(isOldSearch = true).collect {
                _oldSearchQuery.value = it
            }
        }
    }
}