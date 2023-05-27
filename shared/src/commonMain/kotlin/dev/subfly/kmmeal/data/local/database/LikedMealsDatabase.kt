package dev.subfly.kmmeal.data.local.database

import dev.subfly.kmmeal.data.local.entities.LikedMealEntity
import kotlinx.coroutines.flow.Flow

interface LikedMealsDatabase {
    fun getAllLikedMeals(): Flow<List<LikedMealEntity>>
    suspend fun getLikedMeal(mealId: String): LikedMealEntity
    suspend fun likeMeal(meal: LikedMealEntity)
    suspend fun removeFromLikedMeals(meal: LikedMealEntity)
    suspend fun deleteAllLikedMeals()
}