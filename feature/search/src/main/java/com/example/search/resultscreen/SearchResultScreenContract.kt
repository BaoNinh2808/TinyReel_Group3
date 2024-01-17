package com.example.search.resultscreen

data class ViewState(
    val isLoading: Boolean? = null,
    val error: String? = null,
)

sealed class SearchResultEvent(){

}

