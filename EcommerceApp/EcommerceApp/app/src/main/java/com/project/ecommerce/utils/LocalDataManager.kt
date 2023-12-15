package com.project.ecommerce.utils

import android.content.Context
import android.content.SharedPreferences


class LocalDataManager {
    companion object{
        fun setSharedPreference(context: Context, key: String?, value: Boolean) {
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val edit = sharedPref.edit()
            edit.putBoolean(key, value)
            edit.apply()
        }

        fun getSharedPreference(context: Context, key: String?, defaultValue: Boolean): Boolean {
            return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
                .getBoolean(key, defaultValue)
        }

        fun clearSharedPreference(context: Context) {
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE)
            val edit = sharedPref.edit()
            edit.clear()
            edit.apply()
        }

        fun removeSharedPreference(context: Context, key: String?) {
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE)
            val edit = sharedPref.edit()
            edit.remove(key)
            edit.apply()
        }
    }

}