package dev.subfly.kmmeal.data.remote.service

import dev.subfly.kmmeal.data.remote.dto.CategoryDTO
import dev.subfly.kmmeal.data.remote.dto.MealDTO

interface TheMealDBService {
    suspend fun searchMealByName(mealName: String): List<MealDTO>
    suspend fun lookupMealDetailById(mealId: String): MealDTO
    suspend fun lookupSingleRandomMeal(): MealDTO
    suspend fun listAllMealCategories(): List<CategoryDTO>
    suspend fun filterByMainIngredient(ingredient: String): List<MealDTO>
    suspend fun filterByCategory(category: String): List<MealDTO>
    suspend fun filterByArea(area: String): List<MealDTO>
}