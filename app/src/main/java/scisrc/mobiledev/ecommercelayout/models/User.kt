package scisrc.mobiledev.ecommercelayout.ui.models

import android.net.Uri

data class User(
    val profileImageUri: Uri?,
    val fullName: String,
    val email: String,
    val address: String
)
