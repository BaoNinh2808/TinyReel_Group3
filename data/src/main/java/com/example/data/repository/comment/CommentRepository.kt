package com.example.data.repository.comment

import com.example.data.model.CommentList
import com.example.data.source.CommentDataSource.fetchComment
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommentRepository @Inject constructor() {
    fun getComment(videoId: String): Flow<CommentList> {
        return fetchComment(videoId)
    }
}