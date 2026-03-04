package com.yusuf.personaltrainer.navigation

object Routes {
    const val INTRO = "intro"
    const val LOGIN = "login"
    const val OTP = "otp/{phone}/{verId}"
    const val HOME = "home"

    const val BMI = "bmi"

    const val BMR = "bmr"

    const val Meal = "meal"

    const val ToolScreen = "tool_screen"

    const val PERSONAL_INFO = "personal_info"

    const val FOOD_SELECTION = "food_selection/{mealType}"

    fun foodSelectionRoute(mealType: String): String {
        return "food_selection/$mealType"
    }
    fun otpRoute(phone: String, verId: String): String {
        return "otp/$phone/$verId"
    }

}
