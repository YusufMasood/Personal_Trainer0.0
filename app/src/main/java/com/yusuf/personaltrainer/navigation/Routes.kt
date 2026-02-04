package com.yusuf.personaltrainer.navigation

object Routes {
    const val INTRO = "intro"
    const val LOGIN = "login"
    const val OTP = "otp/{phone}/{verId}"
    const val HOME = "home"

    fun otpRoute(phone: String, verId: String): String {
        return "otp/$phone/$verId"
    }
}
