package com.example.data.repository.search

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(){

    //viết tạm như thế này thôi, còn khi làm phải sửa lại thành firebase
    private var oldSearchList = arrayListOf<String>(
        "den vau",
        "di ve nha"
    )

    fun updateSearchQuery(
        userId : Int,
        query : String
    ){
        // gọi firebase để update query mới của người dùng có ID là userID
        oldSearchList.add(query)
    }

    fun getOldSearchQuery(): Flow<List<String>> {
        // gọi firebase để lấy oldSearch hiện tại, trả về 1 list các String
        return flow{
            val _oldSearchList = oldSearchList
            emit(_oldSearchList)
        }
    }

    fun getHotSearchQuery() : Flow<List<String>> {
        // gọi firebase để lấy hotSearch hiện tại, trả về 1 list các String
        return flow{
            val hotSearchList = arrayListOf<String>(
                "tết ở làng địa ngục",
                "mình cùng nhau đón giáng sinh",
                "chị thảo da ua"
            )
            emit(hotSearchList)
        }
    }
}