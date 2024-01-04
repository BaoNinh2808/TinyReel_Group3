package com.example.camera

//import com.example.domain.cameramedia.GetTemplateUseCase
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraMediaViewModel @Inject constructor(
    /*private val getTemplateUseCase: GetTemplateUseCase*/
    private val getTemplateUseCase: String
) : BaseViewModel<ViewState, CameraMediaEvent>() {

    init {
        getTemplates()
    }

    override fun onTriggerEvent(event: CameraMediaEvent) {
        when (event) {
            CameraMediaEvent.EventFetchTemplate -> getTemplates()
        }
    }

    private fun getTemplates() {
//        viewModelScope.launch {
//            getTemplateUseCase().collect {
//                updateState((viewState.value ?: ViewState()).copy(templates = it))
//            }
//        }
    }
}