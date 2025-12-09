package com.example.composittion.domain.entity

import com.example.composittion.domain.entity.GameSettings
import java.io.Serializable

data class GameResult (
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions:Int,
    val gameSettings: GameSettings
): Serializable