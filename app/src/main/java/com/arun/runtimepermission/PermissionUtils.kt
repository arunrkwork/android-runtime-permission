package com.arun.runtimepermission

import android.content.Context
import android.content.SharedPreferences

class PermissionUtils(var context: Context) {

    companion object {
        val PER_CAMERA = "camera"
        val PER_STORAGE = "storage"
        val PER_CONTACTS = "contacts"
    }

    val KEY_SH_PEF_FILE = "permission_utils"
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    init {
        sharedPreferences = context.getSharedPreferences(KEY_SH_PEF_FILE, Context.MODE_PRIVATE)
        editor = sharedPreferences!!.edit()
    }

    fun updatePermissionPreference(permission: String) {
        when(permission) {
            PER_CAMERA -> {
                editor!!.putBoolean(PER_CAMERA, true)
                editor!!.commit()
            }
            PER_STORAGE -> {
                editor!!.putBoolean(PER_STORAGE, true)
                editor!!.commit()
            }
            PER_CONTACTS -> {
                editor!!.putBoolean(PER_CONTACTS, true)
                editor!!.commit()
            }
        }
    }

    fun checkPermissionPreference(permission: String): Boolean {
        var isShown = false
        isShown = when(permission) {
            PER_CAMERA -> sharedPreferences!!
                    .getBoolean(PER_CAMERA, false)

            PER_STORAGE -> sharedPreferences!!
                    .getBoolean(PER_STORAGE, false)

            PER_CONTACTS -> sharedPreferences!!
                    .getBoolean(PER_CONTACTS, false)
            else -> false
        }
        return isShown
    }

}