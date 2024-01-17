package com.example.domain.search

import com.example.data.model.UserModel
import com.example.data.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository : SearchRepository,
) {

    operator fun invoke(isOldSearch : Boolean? = true): Flow<List<String>> {
        if (isOldSearch == true){
            return searchRepository.getOldSearchQuery()
        }
        return searchRepository.getHotSearchQuery()
    }

    fun updateSearchQuery(
        userId : Int,
        query : String
    ){
        searchRepository.updateSearchQuery(userId, query)
    }
}