package com.example.data.repository.home

import com.example.data.model.VideoModel
import com.example.data.source.VideoDataSource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class FirebaseRepository @Inject constructor() {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference().child("TinyReel").child("forYou").child("videos")

    fun getForYouPageFeeds(): Flow<List<VideoModel>> {
        return callbackFlow {
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val videoList = ArrayList<VideoModel>()

                    for (childSnapshot in snapshot.children) {
                        val videoModel = childSnapshot.getValue(VideoModel::class.java)
                        videoModel?.let {
                            videoList.add(it)
                        }
                    }

                    trySend(videoList.shuffled()).isSuccess
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            }

            databaseReference.addListenerForSingleValueEvent(valueEventListener)

            awaitClose { databaseReference.removeEventListener(valueEventListener) }
        }
    }
}
