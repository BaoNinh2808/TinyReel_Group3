package com.example.data.repository.search

import android.util.Log
import com.example.data.model.UserModel
import com.example.data.model.VideoModel
import com.example.data.source.UsersDataSource.zoya
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchResultRepository @Inject constructor() {
    val databaseReference = FirebaseDatabase.getInstance().getReference("TinyReel/forYou/videos")
    companion object {
        fun getQueryResult(searchResultRepository: SearchResultRepository, query : String) : Flow<List<VideoModel>> {

            val deferredResult = CompletableDeferred<List<VideoModel>>()
            searchResultRepository.databaseReference.orderByChild("description")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val result = arrayListOf<VideoModel>()
                        for (data in snapshot.children) {
                            // Extract fields from 'data'
                            val videoId = data.child("videoId").getValue(String::class.java) ?: ""
                            val videoLink = data.child("videoLink").getValue(String::class.java) ?: ""
                            val description = data.child("description").getValue(String::class.java) ?: ""
                            val createdAt = data.child("createdAt").getValue(String::class.java) ?: ""
                            val authorDetailsSnapshot = data.child("authorDetails")
                            val videoStatsSnapshot = data.child("videoStats")

                            Log.d("Des--", " videos: ${description}")

                            if (description.contains(query, ignoreCase = true)) {
                                Log.d("TAG", "Found videos: ${description}")
                            }else{
                                continue
                            }

                            // Create AuthorDetails object
                            val authorDetails = UserModel(
                                bio = authorDetailsSnapshot.child("bio").getValue(String::class.java) ?: "",
                                followers = authorDetailsSnapshot.child("followers").getValue(Long::class.java) ?: 0,
                                following = authorDetailsSnapshot.child("following").getValue(Long::class.java) ?: 0,
                                fullName = authorDetailsSnapshot.child("fullName").getValue(String::class.java) ?: "",
                                likes = authorDetailsSnapshot.child("likes").getValue(Long::class.java) ?: 0,
                                profilePic = authorDetailsSnapshot.child("profilePic").getValue(String::class.java) ?: "",
                                uniqueUserName = authorDetailsSnapshot.child("uniqueUserName").getValue(String::class.java) ?: "",
                                userId = authorDetailsSnapshot.child("userId").getValue(Long::class.java) ?: 0,
                                isVerified = authorDetailsSnapshot.child("verified").getValue(Boolean::class.java) ?: false
                            )

                            // Create VideoStats object
                            val videoStats = VideoModel.VideoStats(
                                comment = videoStatsSnapshot.child("comment")
                                    .getValue(Long::class.java) ?: 0,
                                favourite = videoStatsSnapshot.child("favourite")
                                    .getValue(Long::class.java) ?: 0,
                                like = videoStatsSnapshot.child("like").getValue(Long::class.java)
                                    ?: 0,
                                share = videoStatsSnapshot.child("share").getValue(Long::class.java)
                                    ?: 0,
                                views = videoStatsSnapshot.child("views").getValue(Long::class.java)
                                    ?: 0
                            )

                            // Construct a new VideoModel
                            val video = VideoModel(
                                videoId = videoId,
                                authorDetails = authorDetails,
                                videoLink = videoLink,
                                videoStats = videoStats,
                                description = description,
                                createdAt = createdAt
                            )

                            // Add to the result list
                            result.add(video)
                        }
                        // Complete the deferred with the result list
                        deferredResult.complete(result)
                        }

                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                        Log.w("TAG", "Failed to read value.", error.toException())
                    }
                }
                )
            return flow{
                emit(deferredResult.await())
            }
        }
    }
}