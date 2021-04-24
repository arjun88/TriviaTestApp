package com.appscrip.triviaapp.util

class QuesOptionUtil {
    companion object {
        const val QUESTION1 = "Who is the best Cricketer?"
        const val QUESTION2 = "What are the colors in the Indian national flag?"

        fun getQuizQ(qNum: Int): QuesOp {
            val quesData = ArrayList<QuesOp>()

            val q1Options = ArrayList<Options>()
            q1Options.add(Options("Sachin Tendulkar", false))
            q1Options.add(Options("Virat Kohli", false))
            q1Options.add(Options("Adam Gilchrist", false))
            q1Options.add(Options("Jacques kallis", false))
            quesData.add(QuesOp(QUESTION1, q1Options))

            val q2Options = ArrayList<Options>()
            q2Options.add(Options("White", false))
            q2Options.add(Options("Yellow", false))
            q2Options.add(Options("Orange", false))
            q2Options.add(Options("Green", false))
            quesData.add(QuesOp(QUESTION2, q2Options))

            return quesData[qNum - 1]
        }
    }

    data class QuesOp(var ques: String, var options: List<Options>)

    data class Options(var option: String, var isSel: Boolean)

}