package com.example.composittion.domain.repository

import com.example.composittion.domain.entity.GameSettings
import com.example.composittion.domain.entity.Level
import com.example.composittion.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue:Int,
        countOfOptions:Int
    ): Question

    fun getGameSettings(level: Level): GameSettings

}