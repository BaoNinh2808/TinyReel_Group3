package com.example.myprofile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.DestinationRoute
import com.example.core.base.BaseViewModel
import com.example.creatorprofile.screen.creatorprofile.CreatorProfileEvent
import com.example.creatorprofile.screen.creatorprofile.ViewState
import com.example.data.model.UserModel
import com.example.data.model.VideoModel
import com.example.data.repository.creatorprofile.CreatorProfileRepository
import com.example.data.source.UsersDataSource
import com.example.domain.creatorprofile.EditableCreatorProfileUseCase
import com.example.domain.creatorprofile.GetCreatorProfileUseCase
import com.example.domain.creatorprofile.GetCreatorPublicVideoUseCase
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyProfileViewModel
@Inject constructor(
//class MyProfileViewModel (
//    val userId: Long,
    private val getCreatorProfileUseCase: GetCreatorProfileUseCase,
    private val getCreatorPublicVideoUseCase: GetCreatorPublicVideoUseCase
) : BaseViewModel<ViewState, CreatorProfileEvent>() {
    var userId: Long = 0

    private val _publicVideosList = MutableStateFlow<List<VideoModel>>(arrayListOf())
    val publicVideosList = _publicVideosList.asStateFlow()

    private val _likedVideosList = MutableStateFlow<List<VideoModel>>(arrayListOf())
    val likedVideosList = _likedVideosList.asStateFlow()

    override fun onTriggerEvent(event: CreatorProfileEvent) {
    }

    init {
    }

    fun setId(id: Long) {
        userId = id
    }

    fun getCreatorPublicVideo(): StateFlow<List<VideoModel>> {
        fetchCreatorPublicVideo()
        return publicVideosList
    }

    private fun fetchUser(id: Long) {
        viewModelScope.launch {
            getCreatorProfileUseCase(id).collect {
                updateState(ViewState(creatorProfile = it))
            }
        }
    }

//    fun updateProfile(
//        property: String,
//        newValue: Any
//    ) {
//        getCreatorProfileUseCase.updateProfile(property, newValue)
//    }

    private fun fetchCreatorPublicVideo() {
        viewModelScope.launch {
            getCreatorPublicVideoUseCase(userId).collect {
                _publicVideosList.value = it
            }
        }
    }
}