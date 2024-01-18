package com.example.search.resultscreen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0003H\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/search/resultscreen/SearchResultViewModel;", "Lcom/example/core/base/BaseViewModel;", "Lcom/example/search/resultscreen/ViewState;", "Lcom/example/search/resultscreen/SearchResultEvent;", "searchResultUseCase", "Lcom/example/domain/search/SearchResultUseCase;", "(Lcom/example/domain/search/SearchResultUseCase;)V", "_resultVideoList", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/example/data/model/VideoModel;", "resultVideosList", "Lkotlinx/coroutines/flow/StateFlow;", "getResultVideosList", "()Lkotlinx/coroutines/flow/StateFlow;", "fetchResultVideoList", "", "query", "", "getResultVideoList", "onTriggerEvent", "event", "search_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class SearchResultViewModel extends com.example.core.base.BaseViewModel<com.example.search.resultscreen.ViewState, com.example.search.resultscreen.SearchResultEvent> {
    @org.jetbrains.annotations.NotNull
    private final com.example.domain.search.SearchResultUseCase searchResultUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.data.model.VideoModel>> _resultVideoList = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.data.model.VideoModel>> resultVideosList = null;
    
    @javax.inject.Inject
    public SearchResultViewModel(@org.jetbrains.annotations.NotNull
    com.example.domain.search.SearchResultUseCase searchResultUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.data.model.VideoModel>> getResultVideosList() {
        return null;
    }
    
    @java.lang.Override
    public void onTriggerEvent(@org.jetbrains.annotations.NotNull
    com.example.search.resultscreen.SearchResultEvent event) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.data.model.VideoModel>> getResultVideoList(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
        return null;
    }
    
    private final void fetchResultVideoList(java.lang.String query) {
    }
}