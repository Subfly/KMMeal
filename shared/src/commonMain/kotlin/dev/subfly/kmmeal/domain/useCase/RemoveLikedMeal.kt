package dev.subfly.kmmeal.domain.useCase

import dev.subfly.kmmeal.data.local.database.LikedMealsDatabase
import dev.subfly.kmmeal.domain.mappers.toLikedMealEntity
import dev.subfly.kmmeal.domain.model.MealModel

class RemoveLikedMeal(
    private val database: LikedMealsDatabase
) {
    suspend operator fun invoke(
        meal: MealModel
    ) = database.removeFromLikedMeals(
        meal = meal.toLikedMealEntity()
    )
}