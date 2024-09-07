package com.example.smarttask.cache

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.smarttask.cache.CacheConstants.SELECTED_LANGUAGE
import com.example.smarttask.model.UserDetails


fun getToken(context: Context): String? {
    return Prefs.with(context).getString(CacheConstants.USER_TOKEN, "")
}

fun saveToken(context: Context, token: String) {
    Prefs.with(context).save(CacheConstants.USER_TOKEN, token)
}

fun getDistrictNotificationStatus(context: Context): Int {
    return Prefs.with(context).getInt(CacheConstants.DISTRICT_NOTIFICATIONS, 0)
}

fun setDistrictNotificationStatus(context: Context, status: Int) {
    Prefs.with(context).save(CacheConstants.DISTRICT_NOTIFICATIONS, status)
}

fun getNotificationsCount(context: Context): Int {
    return Prefs.with(context).getInt(CacheConstants.NOTIFICATIONS_COUNT, 0)
}

fun setNotificationsCount(context: Context, status: Int) {
    Prefs.with(context).save(CacheConstants.NOTIFICATIONS_COUNT, status)
}


fun getSelectedLanguage(context: Context): String? {

    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    return preferences.getString(CacheConstants.SELECTED_LANGUAGE, null)
}

fun saveSelectedLanguage(context: Context, languageCode: String) {

    val preferences =
        PreferenceManager.getDefaultSharedPreferences(context)
    val editor = preferences.edit()

    editor.putString(SELECTED_LANGUAGE, languageCode)
    editor.apply()
//    Prefs.with(context).save(CacheConstants.SELECTED_LANGUAGE, languageCode)
}


fun getUser(context: Context): UserDetails? {
    return Prefs.with(context).getObject(CacheConstants.USER_DATA, UserDetails::class.java)
}

fun saveUser(context: Context, user: UserDetails) {
    Prefs.with(context).save(CacheConstants.USER_DATA, user)
}

fun clearAllData(context: Context) {
    Prefs.with(context).removeAll()
}