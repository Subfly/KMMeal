package dev.subfly.kmmeal.data.remote.repository

import dev.subfly.kmmeal.core.utils.constants.MealDBUrls
import dev.subfly.kmmeal.data.remote.dto.CategoryDTO
import dev.subfly.kmmeal.data.remote.dto.CategoryResponse
import dev.subfly.kmmeal.data.remote.dto.MealDTO
import dev.subfly.kmmeal.data.remote.dto.MealResponse
import dev.subfly.kmmeal.data.remote.service.TheMealDBService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MealRepository(
    private val client: HttpClient
): TheMealDBService {

    override suspend fun searchMealByName(mealName: String): List<MealDTO> {
        val response = client
            .get(MealDBUrls.SEARCH_URL + mealName)
            .body<MealResponse>()
        return response.meals ?: emptyList()
    }

    override suspend fun lookupMealDetailById(mealId: String): MealDTO {
        val response = client
            .get(MealDBUrls.LOOKUP_DETAIL + mealId)
            .body<MealResponse>()
        return try {
            val meals = response.meals.orEmpty()
            meals.first()
        } catch (e: NoSuchElementException) {
            throw e
        }
    }

    override suspend fun lookupSingleRandomMeal(): MealDTO {
        val response = client
            .get(MealDBUrls.RANDOM_MEAL)
            .body<MealResponse>()
        return try {
            val meals = response.meals.orEmpty()
            meals.first()
        } catch (e: NoSuchElementException) {
            throw e
        }
    }

    override suspend fun listAllMealCategories(): List<CategoryDTO> {
        val response = client
            .get(MealDBUrls.CATEGORIES)
            .body<CategoryResponse>()
        return response.categories ?: emptyList()
    }

    override suspend fun filterByMainIngredient(ingredient: String): List<MealDTO> {
        val response  = client
            .get(MealDBUrls.FILTER_INGREDIENT + ingredient)
            .body<MealResponse>()
        return response.meals ?: emptyList()
    }

    override suspend fun filterByCategory(category: String): List<MealDTO> {
        val response = client
            .get(MealDBUrls.FILTER_CATEGORY + category)
            .body<MealResponse>()
        return response.meals ?: emptyList()
    }

    override suspend fun filterByArea(area: String): List<MealDTO> {
        val response = client
            .get(MealDBUrls.FILTER_AREA + area)
            .body<MealResponse>()
        return response.meals ?: emptyList()
    }

}