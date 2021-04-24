package com.appscrip.triviaapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appscrip.triviaapp.model.GamePojo
import com.appscrip.triviaapp.repository.GameRepo

class HistoryVM(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private var contex: Context? = null
    var gameInfoList: LiveData<List<GamePojo>> = MutableLiveData()

    init {
        contex = application.applicationContext

        gameInfoList = GameRepo.getGamesPlayed(contex!!)
    }
}