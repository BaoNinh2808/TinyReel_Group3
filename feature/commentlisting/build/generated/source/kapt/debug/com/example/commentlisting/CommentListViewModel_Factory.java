// Generated by Dagger (https://dagger.dev).
package com.example.commentlisting;

import com.example.domain.comment.GetCommentOnVideoUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class CommentListViewModel_Factory implements Factory<CommentListViewModel> {
  private final Provider<GetCommentOnVideoUseCase> getCommentOnVideoUseCaseProvider;

  public CommentListViewModel_Factory(
      Provider<GetCommentOnVideoUseCase> getCommentOnVideoUseCaseProvider) {
    this.getCommentOnVideoUseCaseProvider = getCommentOnVideoUseCaseProvider;
  }

  @Override
  public CommentListViewModel get() {
    return newInstance(getCommentOnVideoUseCaseProvider.get());
  }

  public static CommentListViewModel_Factory create(
      Provider<GetCommentOnVideoUseCase> getCommentOnVideoUseCaseProvider) {
    return new CommentListViewModel_Factory(getCommentOnVideoUseCaseProvider);
  }

  public static CommentListViewModel newInstance(
      GetCommentOnVideoUseCase getCommentOnVideoUseCase) {
    return new CommentListViewModel(getCommentOnVideoUseCase);
  }
}
