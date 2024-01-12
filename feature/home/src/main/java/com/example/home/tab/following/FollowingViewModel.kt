package com.example.home.tab.following

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.domain.following.GetContentCreatorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val getContentCreatorsUseCase: GetContentCreatorsUseCase
) : BaseViewModel<ViewState, FollowingEvent>() {
    override fun onTriggerEvent(event: FollowingEvent) {
    }

    init {
        getContentCreator()
    }

    private fun getContentCreator() {
        viewModelScope.launch {
            getContentCreatorsUseCase().collect {
                updateState(ViewState(contentCreators = it))
            }
        }
    }
}
