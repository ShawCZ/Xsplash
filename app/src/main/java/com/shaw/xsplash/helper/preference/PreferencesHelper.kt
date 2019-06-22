package com.shaw.xsplash.helper.preference

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.preference.PreferenceManager
import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.shaw.xsplash.R
import java.io.File
import com.shaw.xsplash.helper.preference.PreferenceKeys as Keys

fun <T> Preference<T>.getOrDefault(): T = get() ?: defaultValue()!!

fun Preference<Boolean>.invert(): Boolean = getOrDefault().let { set(!it); !it }

class PreferencesHelper(val context: Context) {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    private val rxPrefs = RxSharedPreferences.create(prefs)

    private val defaultDownloadsDir = Uri.fromFile(
        File(
            Environment.getExternalStorageDirectory().absolutePath + File.separator +
                    context.getString(R.string.app_name), "downloads"
        )
    )

    private val defaultBackupDir = Uri.fromFile(
        File(
            Environment.getExternalStorageDirectory().absolutePath + File.separator +
                    context.getString(R.string.app_name), "backup"
        )
    )

    fun startScreen() = prefs.getInt(Keys.startScreen, 1)

    fun clear() = prefs.edit().clear().apply()

    fun connectSSID() = rxPrefs.getString(Keys.connectSSID, "")
}
