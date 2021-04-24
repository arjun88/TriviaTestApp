package com.appscrip.triviaapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.databinding.ActivityHistoryBinding
import com.appscrip.triviaapp.viewmodel.HistoryVM

class HistoryActivity : AppCompatActivity() {
    private var dataBinding: ActivityHistoryBinding? = null
    private var vm: HistoryVM? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_history
        )

        supportActionBar!!.title = "History"

        vm = HistoryVM(application!!)
        dataBinding!!.historyVM = vm
        dataBinding!!.executePendingBindings()

        dataBinding!!.historyRv.layoutManager = LinearLayoutManager(this)
        dataBinding!!.historyRv.setHasFixedSize(true)

        vm!!.gameInfoList.observe(this, { games ->
            Log.e("Game SIZE", "${games.size}")
            val adapter = HistoryAdapter(games)
            dataBinding!!.historyRv.adapter = adapter
            dataBinding!!.invalidateAll()

        }
        )
    }
}