package dev.subfly.kmmeal.domain.useCase

import dev.subfly.kmmeal.data.local.database.LikedMealsDatabase
import dev.subfly.kmmeal.domain.mappers.toMealModel
import dev.subfly.kmmeal.domain.model.MealModel

class GetLikedMeal(
    private val database: LikedMealsDatabase
) {
    suspend operator fun invoke(
        mealId: String
    ): MealModel = database
        .getLikedMeal(mealId = mealId)
        .toMealModel()
}