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
import com.example.data.source.UsersDataSource
import com.example.domain.creatorprofile.EditableCreatorProfileUseCase
import com.example.domain.creatorprofile.GetCreatorProfileUseCase
import com.example.domain.creatorprofile.GetCreatorPublicVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    public val getCreatorProfileUseCase: EditableCreatorProfileUseCase,
    private val getCreatorPublicVideoUseCase: GetCreatorPublicVideoUseCase
) : BaseViewModel<ViewState, CreatorProfileEvent>() {
    val userId: Long? = savedStateHandle[DestinationRoute.PassedKey.USER_ID]

    private val _publicVideosList = MutableStateFlow<List<VideoModel>>(arrayListOf())
    val publicVideosList = _publicVideosList.asStateFlow()

    private val _likedVideosList = MutableStateFlow<List<VideoModel>>(arrayListOf())
    val likedVideosList = _likedVideosList.asStateFlow()

    override fun onTriggerEvent(event: CreatorProfileEvent) {
    }

    init {
        userId?.let {
            fetchUser(it)
            fetchCreatorPublicVideo(it)
        }
    }

    fun fetchUser(id: Long) {
        viewModelScope.launch {
            getCreatorProfileUseCase(id).collect {
                updateState(ViewState(creatorProfile = it))
            }
        }
    }

    fun getUserModel(): UserModel? {
        return viewState.value?.creatorProfile
    }

    fun updateProfile(
        property: String,
        newValue: Any
    ) {
        getCreatorProfileUseCase.updateProfile(property, newValue)
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