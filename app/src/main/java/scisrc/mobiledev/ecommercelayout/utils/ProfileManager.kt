package scisrc.mobiledev.ecommercelayout.utils

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import scisrc.mobiledev.ecommercelayout.ui.models.User

object ProfileManager {
    private const val PREF_NAME = "user_profile"
    private const val KEY_FULL_NAME = "full_name"
    private const val KEY_EMAIL = "email"
    private const val KEY_ADDRESS = "address"
    private const val KEY_PROFILE_IMAGE = "profile_image"

    fun saveUser(context: Context, user: User) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString(KEY_FULL_NAME, user.fullName)
            putString(KEY_EMAIL, user.email)
            putString(KEY_ADDRESS, user.address)
            putString(KEY_PROFILE_IMAGE, user.profileImageUri?.toString())
            apply()
        }
    }

    fun getUser(context: Context): User {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return User(
            profileImageUri = prefs.getString(KEY_PROFILE_IMAGE, null)?.let { Uri.parse(it) },
            fullName = prefs.getString(KEY_FULL_NAME, "ชื่อ-นามสกุล") ?: "",
            email = prefs.getString(KEY_EMAIL, "example@gmail.com") ?: "",
            address = prefs.getString(KEY_ADDRESS, "ยังไม่มีที่อยู่") ?: ""
        )
    }
}
x