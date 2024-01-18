package com.example.commentlisting;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0003H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/commentlisting/CommentListViewModel;", "Lcom/example/core/base/BaseViewModel;", "Lcom/example/commentlisting/ViewState;", "Lcom/example/commentlisting/CommentEvent;", "getCommentOnVideoUseCase", "Lcom/example/domain/comment/GetCommentOnVideoUseCase;", "(Lcom/example/domain/comment/GetCommentOnVideoUseCase;)V", "getContentCreator", "", "onTriggerEvent", "event", "commentlisting_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class CommentListViewModel extends com.example.core.base.BaseViewModel<com.example.commentlisting.ViewState, com.example.commentlisting.CommentEvent> {
    @org.jetbrains.annotations.NotNull
    private final com.example.domain.comment.GetCommentOnVideoUseCase getCommentOnVideoUseCase = null;
    
    @javax.inject.Inject
    public CommentListViewModel(@org.jetbrains.annotations.NotNull
    com.example.domain.comment.GetCommentOnVideoUseCase getCommentOnVideoUseCase) {
        super();
    }
    
    private final void getContentCreator() {
    }
    
    @java.lang.Override
    public void onTriggerEvent(@org.jetbrains.annotations.NotNull
    com.example.commentlisting.CommentEvent event) {
    }
}