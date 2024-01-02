package com.example.tinyreel.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.core.DestinationRoute.CAMERA_ROUTE
import com.example.core.DestinationRoute.FRIENDS_ROUTE
import com.example.core.DestinationRoute.HOME_SCREEN_ROUTE
import com.example.core.DestinationRoute.INBOX_ROUTE
import com.example.core.DestinationRoute.MY_PROFILE_ROUTE
import com.example.theme.R

enum class BottomBarDestination(
    val route: String,
    @StringRes val title: Int? = null,
    @DrawableRes val unFilledIcon: Int,
    @DrawableRes val filledIcon: Int? = null,
    @DrawableRes val darkModeIcon: Int? = null
) {

    HOME(
        route = HOME_SCREEN_ROUTE,
        title = R.string.home,
        unFilledIcon = R.drawable.ic_home,
        filledIcon = R.drawable.ic_home_fill
    ),

    FRIENDS(
        route = FRIENDS_ROUTE,
        title = R.string.friends,
        unFilledIcon = R.drawable.ic_friends,
        filledIcon = R.drawable.ic_friends
    ),

    ADD(
        route = CAMERA_ROUTE,
        unFilledIcon = R.drawable.ic_add_dark,
        darkModeIcon = R.drawable.ic_add_light
    ),

    INBOX(
        route = INBOX_ROUTE,
        title = R.string.inbox,
        unFilledIcon = R.drawable.ic_inbox,
        filledIcon = R.drawable.ic_inbox_fill
    ),

    Profile(
        route = MY_PROFILE_ROUTE,
        title = R.string.profile,
        unFilledIcon = R.drawable.ic_profile,
        filledIcon = R.drawable.ic_profile_fill
    ),

}