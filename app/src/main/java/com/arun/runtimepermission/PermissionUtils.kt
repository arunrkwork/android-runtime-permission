package com.arun.runtimepermission

import android.content.Context
import android.content.SharedPreferences

class PermissionUtils(var context: Context) {

    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    init {
        sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE)
        editor = sharedPreferences!!.edit()
    }

    fun updatePermissionPreference(permission: String) {
        when(permission) {
            "camera" -> {
                editor!!.putBoolean("camera", true)
                editor!!.commit()
            }
            "storage" -> {
                editor!!.putBoolean("storage", true)
                editor!!.commit()
            }
            "contacts" -> {
                editor!!.putBoolean("contacts", true)
                editor!!.commit()
            }
        }
    }

    fun checkPermissionPreference(permission: String): Boolean {
        var isShown = false
        isShown = when(permission) {
            "camera" -> sharedPreferences!!
                    .getBoolean("camera", false)

            "storage" -> sharedPreferences!!
                    .getBoolean("storage", false)

            "contacts" -> sharedPreferences!!
                    .getBoolean("contacts", false)
            else -> false
        }
        return isShown
    }

}