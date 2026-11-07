package com.majician.app.data

data class UpdateInfo(
    val versionCode: Int = 1,
    val versionName: String = "1.0",
    val forceUpdate: Boolean = false,
    val message: String = "",
    val downloadUrl: String = ""
)
