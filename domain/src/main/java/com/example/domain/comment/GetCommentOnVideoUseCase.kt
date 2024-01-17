package com.example.domain.comment

import com.example.data.model.CommentList
import com.example.data.repository.comment.CommentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCommentOnVideoUseCase @Inject constructor(private val commentRepository: CommentRepository) {
    operator fun invoke(videoId: String): Flow<CommentList> {
        return commentRepository.getComment(videoId)
    }
}