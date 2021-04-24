package com.appscrip.triviaapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appscrip.triviaapp.model.GamePojo
import com.appscrip.triviaapp.repository.GameRepo
import com.appscrip.triviaapp.util.PrefUtil
import com.appscrip.triviaapp.util.QuesOptionUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SummaryVM(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private var contex: Context? = null
    var q1: MutableLiveData<String> = MutableLiveData()
    var q2: MutableLiveData<String> = MutableLiveData()
    var a1: ObservableField<String> = ObservableField()
    var a2: ObservableField<String> = ObservableField()
    var user: MutableLiveData<String> = MutableLiveData()
    var prefUtil: PrefUtil? = null

    var gameInfo: LiveData<GamePojo> = MutableLiveData()
    var gameInfoList: LiveData<List<GamePojo>> = MutableLiveData()

    var clickViewHistory: MutableLiveData<Boolean>? = MutableLiveData()
    var clickFinish: MutableLiveData<Boolean>? = MutableLiveData()

    init {
        clickViewHistory!!.value = false
        clickFinish!!.value = false
        contex = application.applicationContext
        prefUtil = PrefUtil(contex!!)
        a1.set("")
        a2.set("")

        user.value = "Hello ${prefUtil!!.getUser()}"
        q1.value = QuesOptionUtil.getQuizQ(1).ques
        q2.value = QuesOptionUtil.getQuizQ(2).ques

        gameInfoList = GameRepo.getGamesPlayed(contex!!)
        gameInfo = GameRepo.getGameInfo(contex!!)

    }

    fun onClickFinish() {
        clickFinish!!.value = true
    }

    fun onClickHistory() {
        clickViewHistory!!.value = true
    }
}
