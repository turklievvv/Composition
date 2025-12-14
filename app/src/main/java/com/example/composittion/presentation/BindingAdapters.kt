package com.example.composittion.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composittion.R
import com.example.composittion.domain.entity.GameResult

interface onOptionClickListener {
    fun onOptionClick(option:Int)
}

@BindingAdapter("requireAnswers")
fun bindRequiresAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.finishText1), count.toString()
    )
}

@BindingAdapter("requireAnswers2")
fun bindRequiresAnswers2(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.finishText2), count.toString()
    )
}

@BindingAdapter("requireAnswers3")
fun bindRequiresAnswers3(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.finishText3), count.toString()
    )
}

@BindingAdapter("requireAnswers4")
fun bindRequiresAnswers4(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.finishText4),
        getPercentOfRightAnswers(gameResult).toString()
    )
}

private fun getPercentOfRightAnswers(gameResult: GameResult): Int {
    with(gameResult) {
        return if (countOfRightAnswers == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }
}

@BindingAdapter("resultWinnerText")
fun bindWinnerText(textView: TextView, winner: Boolean) {
    textView.text = getWinnerText(winner)
}

private fun getWinnerText(winner: Boolean): String {
    return if (winner) {
        "Winner!!"
    } else {
        "Loser"
    }
}

@BindingAdapter("numberAsText")
fun bindSumText(textView: TextView, count: Int) {
    textView.text = count.toString()
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(enough, textView.context))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(enough,progressBar.context)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(goodState: Boolean, context: Context): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView,clickListener: onOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}