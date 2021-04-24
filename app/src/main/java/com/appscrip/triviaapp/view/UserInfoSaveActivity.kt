package com.appscrip.triviaapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.databinding.ActivityUserInfoSaveBinding
import com.appscrip.triviaapp.viewmodel.UserInfoSaveVM

class UserInfoSaveActivity : AppCompatActivity() {
    private var dataBinding: ActivityUserInfoSaveBinding? = null
    private var qOpVM: UserInfoSaveVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_user_info_save
        )

        qOpVM = UserInfoSaveVM(application!!)
        dataBinding!!.userSaveVM = qOpVM
        dataBinding!!.executePendingBindings()

        qOpVM!!.nextClicked.observe(
            this, { clicked ->
                if (clicked == true) {
                    if(qOpVM!!.user.value!!.isEmpty()){
                        showToast("Please provide your name.")
                    }
                    else {
                        val moveTo = Intent(this, QuestionOptionActivity::class.java)
                        startActivity(moveTo)
                    }
                }
            }
        )
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}