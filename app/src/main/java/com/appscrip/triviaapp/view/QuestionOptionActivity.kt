package com.appscrip.triviaapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.databinding.ActivityQuestionOptionBinding
import com.appscrip.triviaapp.viewmodel.QopVM

class QuestionOptionActivity : AppCompatActivity(), OptionSelectable {
    private var dataBinding: ActivityQuestionOptionBinding? = null
    private var qOpVM: QopVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_question_option
        )

        qOpVM = QopVM(application!!)
        qOpVM!!.qId.value = 1
        dataBinding!!.qOpVM = qOpVM
        dataBinding!!.executePendingBindings()

        dataBinding!!.optionsRv.layoutManager = LinearLayoutManager(this)
        dataBinding!!.optionsRv.setHasFixedSize(true)
        dataBinding!!.optionsRv.adapter = QuesOpItemAdapter(qOpVM!!.qId.value, this)

        qOpVM!!.qId.observe(this, {
            dataBinding!!.optionsRv.adapter = QuesOpItemAdapter(qOpVM!!.qId.value, this)
            dataBinding!!.invalidateAll()
        })

        qOpVM!!.AddedRow.observe(
            this, { value ->
                run {
                    if (value != 0L) {
                        showToast("Saved!")

                        val moveTo = Intent(this, SummaryActivity::class.java)
                        startActivity(moveTo)
                        finishAffinity()
                    }
                }
            }
        )
    }

    override fun onOptionSel(option: String, qId: Int) {
        if (qId == 1) {
            qOpVM!!.OPTION1SEL = option
        } else {
            qOpVM!!.OPTION2SEL = option
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}