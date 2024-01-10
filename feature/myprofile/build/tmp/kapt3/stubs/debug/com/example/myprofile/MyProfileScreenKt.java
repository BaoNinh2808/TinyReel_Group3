package com.example.myprofile;

import androidx.compose.foundation.*;
import androidx.compose.material3.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.Alignment;
import androidx.navigation.NavController;
import androidx.compose.ui.Modifier;
import com.example.theme.R;
import com.example.core.DestinationRoute;
import com.example.data.model.UserModel;
import com.example.data.model.SocialMediaType;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0016\u0010\u0007\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u001a\u0016\u0010\n\u001a\u00020\u0001*\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007\u00a8\u0006\u000e"}, d2 = {"LoggedInProfileScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/example/myprofile/CreatorProfileViewModel;", "MyProfileScreen", "UnAuthorizedInboxScreen", "onClickSignup", "Lkotlin/Function0;", "ProfileDetails", "Landroidx/compose/foundation/layout/ColumnScope;", "creatorProfile", "Lcom/example/data/model/UserModel;", "myprofile_debug"})
public final class MyProfileScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void MyProfileScreen(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void UnAuthorizedInboxScreen(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClickSignup) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void LoggedInProfileScreen(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull
    com.example.myprofile.CreatorProfileViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ProfileDetails(@org.jetbrains.annotations.NotNull
    androidx.compose.foundation.layout.ColumnScope $this$ProfileDetails, @org.jetbrains.annotations.Nullable
    com.example.data.model.UserModel creatorProfile) {
    }
}