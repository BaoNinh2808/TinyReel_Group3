package com.example.myprofile;

import android.util.Log;
import androidx.lifecycle.SavedStateHandle;
import com.example.core.base.BaseViewModel;
import com.example.data.model.VideoModel;
import com.example.domain.creatorprofile.GetCreatorProfileUseCase;
import com.example.domain.creatorprofile.GetCreatorPublicVideoUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0017H\u0002J\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0017H\u0002J\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u0003H\u0016R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0015\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006!"}, d2 = {"Lcom/example/myprofile/CreatorProfileViewModel;", "Lcom/example/core/base/BaseViewModel;", "Lcom/example/myprofile/ViewState;", "Lcom/example/myprofile/CreatorProfileEvent;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "getCreatorProfileUseCase", "Lcom/example/domain/creatorprofile/GetCreatorProfileUseCase;", "getCreatorPublicVideoUseCase", "Lcom/example/domain/creatorprofile/GetCreatorPublicVideoUseCase;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/example/domain/creatorprofile/GetCreatorProfileUseCase;Lcom/example/domain/creatorprofile/GetCreatorPublicVideoUseCase;)V", "_likedVideosList", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/example/data/model/VideoModel;", "_publicVideosList", "likedVideosList", "Lkotlinx/coroutines/flow/StateFlow;", "getLikedVideosList", "()Lkotlinx/coroutines/flow/StateFlow;", "publicVideosList", "getPublicVideosList", "userId", "", "getUserId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "fetchCreatorPublicVideo", "", "id", "fetchUser", "onTriggerEvent", "event", "myprofile_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class CreatorProfileViewModel extends com.example.core.base.BaseViewModel<com.example.myprofile.ViewState, com.example.myprofile.CreatorProfileEvent> {
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.SavedStateHandle savedStateHandle = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.domain.creatorprofile.GetCreatorProfileUseCase getCreatorProfileUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.domain.creatorprofile.GetCreatorPublicVideoUseCase getCreatorPublicVideoUseCase = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long userId = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.data.model.VideoModel>> _publicVideosList = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.data.model.VideoModel>> publicVideosList = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.data.model.VideoModel>> _likedVideosList = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.data.model.VideoModel>> likedVideosList = null;
    
    @javax.inject.Inject
    public CreatorProfileViewModel(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull
    com.example.domain.creatorprofile.GetCreatorProfileUseCase getCreatorProfileUseCase, @org.jetbrains.annotations.NotNull
    com.example.domain.creatorprofile.GetCreatorPublicVideoUseCase getCreatorPublicVideoUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.data.model.VideoModel>> getPublicVideosList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.data.model.VideoModel>> getLikedVideosList() {
        return null;
    }
    
    @java.lang.Override
    public void onTriggerEvent(@org.jetbrains.annotations.NotNull
    com.example.myprofile.CreatorProfileEvent event) {
    }
    
    private final void fetchUser(long id) {
    }
    
    private final void fetchCreatorPublicVideo(long id) {
    }
}