package com.appscrip.triviaapp.util

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CalendarUtils {
    companion object {

        fun getDateTime(): String {
            val curDate: String

            val sdf = SimpleDateFormat("dd MMM yyyy hh:mm a")

            curDate = sdf.format(Date())

            Log.e("DATE", "today: $curDate")

            return curDate
        }
    }


}
