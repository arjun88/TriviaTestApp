package com.appscrip.triviaapp.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.model.GamePojo
import com.appscrip.triviaapp.repository.GameRepo
import com.appscrip.triviaapp.util.QuesOptionUtil
import com.google.android.material.textview.MaterialTextView

class HistoryAdapter() : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {
    private var gamesList:List<GamePojo> = ArrayList()
    private var context: Context? = null

    constructor(gamesList: List<GamePojo>?) : this() {
        this.gamesList = gamesList!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.game_his_item,
                parent, false
            )
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val game = gamesList[position]
       holder.gameNumTV!!.text = "Game: ${game.id}"
        holder.userTV!!.text = "Name: ${game.user}"

        holder.q1TV!!.text = "Q: ${QuesOptionUtil.getQuizQ(1).ques}"
        holder.q2Tv!!.text = "Q: ${QuesOptionUtil.getQuizQ(2).ques}"

        holder.a1Tv!!.text = "Ans: ${game.q1Ans}"
        holder.a2Tv!!.text = "Ans: ${game.q2Ans}"

        holder.dateTV!!.text = game.dateTime
    }



    override fun getItemCount(): Int {
        return gamesList.size
    }

    class HistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gameNumTV: MaterialTextView? = null
        var userTV: MaterialTextView? = null
        var dateTV: MaterialTextView? = null
        var q1TV: MaterialTextView? = null
        var q2Tv: MaterialTextView? = null
        var a1Tv: MaterialTextView? = null
        var a2Tv: MaterialTextView? = null

        init {
            gameNumTV = itemView.findViewById(R.id.game_num_tv)
            userTV = itemView.findViewById(R.id.user_tv)
            dateTV = itemView.findViewById(R.id.game_date_tv)
            q1TV = itemView.findViewById(R.id.game_q1_tv)
            q2Tv = itemView.findViewById(R.id.game_q2_tv)
            a1Tv = itemView.findViewById(R.id.game_a1_tv)
            a2Tv = itemView.findViewById(R.id.game_a2_tv)

        }

    }

}