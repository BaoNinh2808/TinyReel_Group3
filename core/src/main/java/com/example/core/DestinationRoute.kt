package com.example.core

import com.example.core.DestinationRoute.PassedKey.USER_ID
import com.example.core.DestinationRoute.PassedKey.VIDEO_INDEX

object DestinationRoute {
    const val HOME_SCREEN_ROUTE = "home_screen_route"
    const val COMMENT_BOTTOM_SHEET_ROUTE = "comment_bottom_sheet_route"
    const val CREATOR_PROFILE_ROUTE = "creator_profile_route"

    const val CREATOR_VIDEO_ROUTE = "creator_video_route"
    const val FORMATTED_COMPLETE_CREATOR_VIDEO_ROUTE =
        "$CREATOR_VIDEO_ROUTE/{$USER_ID}/{$VIDEO_INDEX}"

    const val INBOX_ROUTE = "inbox_route"
    const val MY_PROFILE_ROUTE = "my_profile_route"
    const val MY_PROFILE_SETTING_ROUTE = "my_profile_setting_route"
    const val FRIENDS_ROUTE = "friends_route"
    const val CAMERA_ROUTE = "camera_route"
    const val POST_ROUTE = "post_route"
    const val SEARCH_ROUTE = "search_route"

    const val AUTHENTICATION_ROUTE = "authentication_route"
    const val SIGNUP_ROUTE = "signup_route"
    const val LOGIN_OR_SIGNUP_WITH_PHONE_EMAIL_ROUTE = "login_signup_phone_email_route"

    const val SETTING_ROUTE="setting_route"
    object PassedKey {
        const val USER_ID = "user_id"
        const val VIDEO_INDEX = "video_index"
    }
}