package com.example.composittion.domain.entity

import android.os.Parcelable
import com.example.composittion.domain.entity.GameSettings
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
) : Parcelable