package com.example.data.repository.search

import com.example.data.model.VideoModel
import com.example.data.source.UsersDataSource.zoya
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchResultRepository @Inject constructor() {
    fun getQueryResult(query : String) : Flow<List<VideoModel>> {
        val zoya_vid1 = VideoModel(
            videoId = "zoya_vid1",
            authorDetails = zoya,
            videoLink = "zoya_vid1.mp4",
            videoStats = VideoModel.VideoStats(
                like = 94201,
                comment = 362,
                share = 54,
                favourite = 626,
                views = 904440
            ),
            description = "Draft video testing  #foryou #fyp #compose #tik",
            audioModel = null, hasTag = listOf(),
        )
        val zoya_vid2 = VideoModel(
            videoId = "zoya_vid2",
            authorDetails = zoya,
            videoLink = "zoya_vid2.mp4",
            videoStats = VideoModel.VideoStats(
                like = 624524,
                comment = 2577,
                share = 98,
                favourite = 78,
                views = 23904000
            ),
            description = "Draft video testing  #foryou #fyp #compose #tik",
            audioModel = null, hasTag = listOf(),
        )

        val zoya_vid3 = VideoModel(
            videoId = "zoya_vid3",
            authorDetails = zoya,
            videoLink = "zoya_vid3.mp4",
            videoStats = VideoModel.VideoStats(
                like = 563463,
                comment = 4297,
                share = 2113,
                favourite = 1431,
                views = 40300
            ),
            description = "Draft video testing  #foryou #fyp #compose #tik",
            audioModel = null, hasTag = listOf(),
        )
        val zoya_vid4 = VideoModel(
            videoId = "zoya_vid4",
            authorDetails = zoya,
            videoLink = "zoya_vid4.mp4",
            videoStats = VideoModel.VideoStats(
                like = 789349,
                comment = 2577,
                share = 797,
                favourite = 13,
                views = 39000
            ),
            description = "Draft video testing  #foryou #fyp #compose #tik",
            audioModel = null, hasTag = listOf(),
        )
        val zoya_vid5 = VideoModel(
            videoId = "zoya_vid5",
            authorDetails = zoya,
            videoLink = "zoya_vid5.mp4",
            videoStats = VideoModel.VideoStats(
                like = 682482,
                comment = 7938,
                share = 9821,
                favourite = 78,
                views = 300000
            ),
            description = "Draft video testing  #foryou #fyp #compose #tik",
            audioModel = null, hasTag = listOf(),
        )
        val zoya_vid6 = VideoModel(
            videoId = "zoya_vid6",
            authorDetails = zoya,
            videoLink = "zoya_vid6.mp4",
            videoStats = VideoModel.VideoStats(
                like = 45172,
                comment = 1987,
                share = 987,
                favourite = 102,
                views = 2904100
            ),
            description = "Draft video testing  #foryou #fyp #compose #tik",
            audioModel = null, hasTag = listOf(),
        )

        val result = arrayListOf<VideoModel>(zoya_vid1, zoya_vid2, zoya_vid3, zoya_vid4, zoya_vid5, zoya_vid6)

        return flow{
            emit(result)
        }
    }
}