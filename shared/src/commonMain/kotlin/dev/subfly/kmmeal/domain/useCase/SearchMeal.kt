package dev.subfly.kmmeal.domain.useCase

import dev.subfly.kmmeal.core.model.Resource
import dev.subfly.kmmeal.core.utils.enums.SearchFilterType
import dev.subfly.kmmeal.data.remote.repository.MealRepository
import dev.subfly.kmmeal.domain.mappers.toMealModel
import dev.subfly.kmmeal.domain.model.MealModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SearchMeal(
    private val repository: MealRepository
) {
    suspend operator fun invoke(
        by: SearchFilterType = SearchFilterType.NONE,
        query: String,
    ): Flow<Resource<List<MealModel>>> = flow {
        emit(Resource.Loading)
        val response = when (by) {
            SearchFilterType.INGREDIENT -> {
                repository.filterByMainIngredient(query)
            }
            SearchFilterType.CATEGORY -> {
                repository.filterByCategory(query)
            }
            SearchFilterType.AREA -> {
                repository.filterByArea(query)
            }
            SearchFilterType.NONE -> {
                repository.searchMealByName(query)
            }
        }.map {
            it.toMealModel()
        }
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error("An unexpected error happened, please try again..."))
    }
}