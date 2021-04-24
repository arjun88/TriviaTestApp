package com.appscrip.triviaapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.appscrip.triviaapp.model.GamePojo
import com.appscrip.triviaapp.repository.GameRepo
import com.appscrip.triviaapp.util.CalendarUtils
import com.appscrip.triviaapp.util.PrefUtil
import com.appscrip.triviaapp.util.QuesOptionUtil

class QopVM(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private var contex: Context? = null
    var qId: MutableLiveData<Int> = MutableLiveData()
    var question: ObservableField<String> = ObservableField()
    var options: MutableLiveData<List<QuesOptionUtil.Options>> = MutableLiveData()

    var OPTION1SEL = ""
    var OPTION2SEL = ""
    var prefUtil: PrefUtil? = null

    var AddedRow: MutableLiveData<Long> = MutableLiveData()

    init {
        AddedRow.value = 0L
        contex = application.applicationContext
        prefUtil = PrefUtil(contex!!)
        qId.value = 1
        question.set(QuesOptionUtil.getQuizQ(qId.value!!).ques)
        options.value = QuesOptionUtil.getQuizQ(qId.value!!).options
    }

    fun onNextClick() {
        if (qId.value == 2) {
            val data =
                GamePojo(
                    user = prefUtil!!.getUser(),
                    q1Ans = OPTION1SEL,
                    q2Ans = OPTION2SEL,
                    dateTime = CalendarUtils.getDateTime()
                )
            insertData(contex!!, data)
        } else {
            qId.value = qId.value!! + 1
            question.set(QuesOptionUtil.getQuizQ(qId.value!!).ques)
            options.value = QuesOptionUtil.getQuizQ(qId.value!!).options

            Log.e("OPTION 1 Sel: ", OPTION1SEL)
            Log.e("OPTION 2 Sel: ", OPTION2SEL)
        }

    }

    private fun insertData(context: Context, gameData: GamePojo) {
        val added = GameRepo.insertData(context, gameData)
        AddedRow.value = 1
        Log.e("SAVED to DB: ", "$added")

        AddedRow.value = added

    }

}