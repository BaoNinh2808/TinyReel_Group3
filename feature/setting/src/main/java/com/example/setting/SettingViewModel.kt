package com.example.setting

import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
) : BaseViewModel<ViewState, SettingEvent>() {

    init {
        updateState(ViewState(settingUiData = settingUiModel))
    }

    override fun onTriggerEvent(event: SettingEvent) {
    }

}