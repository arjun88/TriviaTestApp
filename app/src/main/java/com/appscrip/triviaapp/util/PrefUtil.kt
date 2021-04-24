package com.appscrip.triviaapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PrefUtil(context: Context) {
    var sharedPrefInstance: SharedPreferences? = null
    init {
        sharedPrefInstance = context.getSharedPreferences(
            "TRIVIA", Context.MODE_PRIVATE
        )
    }

    fun saveUser(name: String){
        val editor = sharedPrefInstance!!.edit()
        editor.putString("USER", name)
        editor.apply()
    }

    fun getUser(): String{
        val user = sharedPrefInstance!!.getString("USER","")
        return user!!
    }
}