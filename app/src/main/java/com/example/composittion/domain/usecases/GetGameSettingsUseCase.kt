package com.example.composittion.domain.usecases

import com.example.composittion.domain.entity.GameSettings
import com.example.composittion.domain.entity.Level
import com.example.composittion.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }
}