package com.example.domain.foryou

import com.example.data.model.VideoModel
import com.example.data.repository.home.FirebaseRepository
import com.example.data.repository.home.ForYouRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//class GetForYouPageFeedUseCase @Inject constructor(private val forYouRepository: ForYouRepository) {
//    operator fun invoke(): Flow<List<VideoModel>> {
//        return forYouRepository.getForYouPageFeeds()
//    }
//}

class GetForYouPageFeedUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    operator fun invoke(): Flow<List<VideoModel>> {
//        get videos back from firbase realtime database

        return firebaseRepository.getForYouPageFeeds()
    }
}
