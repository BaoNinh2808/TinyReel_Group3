package com.example.creatorprofile.screen.creatorprofile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.DestinationRoute.PassedKey.USER_ID
import com.example.core.base.BaseViewModel
import com.example.data.model.VideoModel
import com.example.domain.creatorprofile.GetCreatorProfileUseCase
import com.example.domain.creatorprofile.GetCreatorPublicVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatorProfileViewModel
@Inject constructor(
//    private val savedStateHandle: SavedStateHandle,
    private val _userId: Long,
    private val getCreatorProfileUseCase: GetCreatorProfileUseCase,
    private val getCreatorPublicVideoUseCase: GetCreatorPublicVideoUseCase
) : BaseViewModel<ViewState, CreatorProfileEvent>() {
//    val userId: Long? = savedStateHandle[USER_ID]
    val userId: Long = _userId

    private val _publicVideosList = MutableStateFlow<List<VideoModel>>(arrayListOf())
    val publicVideosList = _publicVideosList.asStateFlow()

    private val _likedVideosList = MutableStateFlow<List<VideoModel>>(arrayListOf())
    val likedVideosList = _likedVideosList.asStateFlow()

    override fun onTriggerEvent(event: CreatorProfileEvent) {
    }

//    init {
//        userId?.let {
//            fetchUser(it)
//            fetchCreatorPublicVideo(it)
//        }
//    }

    init {
        _userId?.let {
            fetchUser(it)
            fetchCreatorPublicVideo(it)
        }
    }

    private fun fetchUser(id: Long) {
        viewModelScope.launch {
            getCreatorProfileUseCase(id).collect {
                updateState(ViewState(creatorProfile = it))
            }
        }
    }

    private fun fetchCreatorPublicVideo(id: Long) {
        viewModelScope.launch {
            getCreatorPublicVideoUseCase(id).collect {
                Log.d("d", "my video si ${it}")
                _publicVideosList.value = it
            }
        }
    }
}