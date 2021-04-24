package com.appscrip.triviaapp.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.util.QuesOptionUtil
import com.google.android.material.textview.MaterialTextView

class QuesOpItemAdapter() : RecyclerView.Adapter<QuesOpItemAdapter.QopListHolder>() {
    private var qId: Int? = 1
    private var options = ArrayList<QuesOptionUtil.Options>()
    private var optionSelectable: OptionSelectable? = null

    private var option1Sel = ""
    private var option2Set = HashSet<String>()
    private var context: Context? = null

    constructor(qId: Int?, optionSelectable: OptionSelectable?) : this() {
        this.qId = qId
        this.options.addAll(QuesOptionUtil.getQuizQ(qId!!).options)
        this.optionSelectable = optionSelectable
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QopListHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.option_list_item,
                parent, false
            )
        return QopListHolder(view)
    }

    override fun onBindViewHolder(holder: QopListHolder, position: Int) {
        holder.optionTV!!.text = options[position].option
        if (qId == 1) {
            holder.optionTV!!.setOnClickListener {
                clearOtherRadioSel(position)
                notifyDataSetChanged()
            }

            val dLeftSel = ContextCompat.getDrawable(context!!, R.drawable.ic_rbtn_checked)
            val dLeftUnSel = ContextCompat.getDrawable(context!!, R.drawable.ic_radio_btn)

            Log.e("SEL $position : ", options[position].isSel.toString())

            if(options[position].isSel){
                holder.optionTV!!.setCompoundDrawablesWithIntrinsicBounds(
                    dLeftSel,null,null,null
                )
            }
            else{
                holder.optionTV!!.setCompoundDrawablesWithIntrinsicBounds(
                    dLeftUnSel,null,null,null
                )
            }
        } else {

            holder.optionTV!!.setOnClickListener {
                checkBoxSel(position)

                notifyDataSetChanged()
            }

            val dLeftSel = ContextCompat.getDrawable(context!!, R.drawable.ic_check_box)
            val dLeftUnSel = ContextCompat.getDrawable(context!!, R.drawable.ic_chk_box_empty)

            if(options[position].isSel){
                holder.optionTV!!.setCompoundDrawablesWithIntrinsicBounds(
                    dLeftSel,null,null,null
                )
            }
            else{
                holder.optionTV!!.setCompoundDrawablesWithIntrinsicBounds(
                    dLeftUnSel,null,null,null
                )
            }

        }
    }

    private fun clearOtherRadioSel(pos: Int) {

        for (option in options) {
           option.isSel = false
        }

        options[pos].isSel = true
        option1Sel = options[pos].option
        optionSelectable!!.onOptionSel(
            option1Sel, qId!!
        )

    }

    private fun checkBoxSel(pos: Int) {

       /* for (option in options) {
            options[pos].isSel = false
        }*/

        options[pos].isSel = !options[pos].isSel

        if(options[pos].isSel)
        option2Set.add(options[pos].option)
        else{
            option2Set.remove(options[pos].option)
        }

        optionSelectable!!.onOptionSel(
            option2Set.joinToString(separator = ","), qId!!
        )

      //  options[pos].isSel = true

    }

    override fun getItemCount(): Int {
        return options.size
    }

    class QopListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var optionTV: MaterialTextView? = null

        init {
            optionTV = itemView.findViewById(R.id.option_r)

        }

    }

}