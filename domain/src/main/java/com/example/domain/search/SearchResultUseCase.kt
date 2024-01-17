package com.example.domain.search

import com.example.data.model.VideoModel
import com.example.data.repository.search.SearchResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchResultUseCase @Inject constructor(
    private val searchResultRepository : SearchResultRepository
) {
    operator fun invoke(query : String): Flow<List<VideoModel>> {
        return searchResultRepository.getQueryResult(query)
    }
}