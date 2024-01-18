package com.example.search.searchscreen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J\u0016\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\nR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/search/searchscreen/SearchScreenViewModel;", "Lcom/example/core/base/BaseViewModel;", "Lcom/example/search/resultscreen/ViewState;", "Lcom/example/search/resultscreen/SearchResultEvent;", "searchUseCase", "Lcom/example/domain/search/SearchUseCase;", "(Lcom/example/domain/search/SearchUseCase;)V", "_hotSearchQuery", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "_oldSearchQuery", "hotSearchQuery", "Lkotlinx/coroutines/flow/StateFlow;", "getHotSearchQuery", "()Lkotlinx/coroutines/flow/StateFlow;", "oldSearchQuery", "getOldSearchQuery", "fetchHotSearchQuery", "", "fetchOldSearchQuery", "onTriggerEvent", "event", "updateSearchQuery", "userId", "", "query", "search_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class SearchScreenViewModel extends com.example.core.base.BaseViewModel<com.example.search.resultscreen.ViewState, com.example.search.resultscreen.SearchResultEvent> {
    @org.jetbrains.annotations.NotNull
    private final com.example.domain.search.SearchUseCase searchUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _oldSearchQuery = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _hotSearchQuery = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> oldSearchQuery = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> hotSearchQuery = null;
    
    @javax.inject.Inject
    public SearchScreenViewModel(@org.jetbrains.annotations.NotNull
    com.example.domain.search.SearchUseCase searchUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getOldSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getHotSearchQuery() {
        return null;
    }
    
    @java.lang.Override
    public void onTriggerEvent(@org.jetbrains.annotations.NotNull
    com.example.search.resultscreen.SearchResultEvent event) {
    }
    
    public final void updateSearchQuery(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    private final void fetchHotSearchQuery() {
    }
    
    private final void fetchOldSearchQuery() {
    }
}