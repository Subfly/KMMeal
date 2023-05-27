package dev.subfly.kmmeal.domain.useCase

import dev.subfly.kmmeal.data.local.database.LikedMealsDatabase

class DeleteAllLikedMeals(
    private val database: LikedMealsDatabase
) {
    suspend operator fun invoke() = database.deleteAllLikedMeals()
}