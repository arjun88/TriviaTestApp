package com.appscrip.triviaapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.databinding.ActivitySummaryBinding
import com.appscrip.triviaapp.viewmodel.SummaryVM

class SummaryActivity : AppCompatActivity() {
    private var dataBinding: ActivitySummaryBinding? = null
    private var summaryVM: SummaryVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_summary
        )
        supportActionBar!!.title = "Summary"

        summaryVM = SummaryVM(application!!)
        dataBinding!!.summaryVM = summaryVM
        dataBinding!!.executePendingBindings()

        summaryVM!!.gameInfoList.observe(this, { games ->
            Log.e("Game SIZE", "${games.size}")
            dataBinding!!.invalidateAll()
        }
        )

        summaryVM!!.gameInfo.observe(this,
            { game ->
                run {
                    Log.e("Game Q1Ans", "${game.q1Ans}")

                    summaryVM!!.a1.set(game!!.q1Ans)
                    summaryVM!!.a2.set(game.q2Ans)
                }
            }
        )
        dataBinding!!.invalidateAll()

        summaryVM!!.clickViewHistory!!.observe(
            this, { click ->
                run {
                    if (click == true) {
                        val moveTo = Intent(this, HistoryActivity::class.java)
                        startActivity(moveTo)
                    }
                }
            }
        )

        summaryVM!!.clickFinish!!.observe(
            this, { click ->
                run {
                    if (click == true) {
                        val moveTo = Intent(this, UserInfoSaveActivity::class.java)
                        startActivity(moveTo)
                        finishAffinity()
                    }
                }
            }
        )
    }

}