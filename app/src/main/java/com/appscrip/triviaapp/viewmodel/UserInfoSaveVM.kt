package com.appscrip.triviaapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.appscrip.triviaapp.util.PrefUtil

class UserInfoSaveVM(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private var contex: Context? = null

    var user: MutableLiveData<String> = MutableLiveData()
    var nextClicked: MutableLiveData<Boolean> = MutableLiveData()

    init {
        nextClicked.value = false
        user.value = ""
        contex = application.applicationContext
    }

    fun onClickNext() {
        Log.e("NEXT","")
        val prefUtil = PrefUtil(contex!!)
        prefUtil.saveUser(user.value!!)



        nextClicked.value = true
    }
}