package br.com.luanacostaa.glgcar

import android.content.Context
import android.content.SharedPreferences

object Prefs {
    val PREF_ID = "GLG"

    // retorna o armazém de preferências PREF_ID
    private fun prefs(): SharedPreferences {
        val context = GLGApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID, 0)
    }

    fun setBoolean(flag: String, valor: Boolean) =  prefs().edit().putBoolean(flag, valor).apply()

    fun getBoolean(flag: String) = prefs().getBoolean(flag, false)

    fun setString(flag: String, valor: String) =  prefs().edit().putString(flag, valor).apply()

    fun getString(flag: String) = prefs().getString(flag, "")

}