package dev.subfly.kmmeal.domain.useCase

import dev.subfly.kmmeal.core.model.Resource
import dev.subfly.kmmeal.data.remote.repository.MealRepository
import dev.subfly.kmmeal.domain.mappers.toCategoryModel
import dev.subfly.kmmeal.domain.model.CategoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FetchCategories(
    private val repository: MealRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<CategoryModel>>> = flow {
        emit(Resource.Loading)
        val response = repository
            .listAllMealCategories()
            .map { it.toCategoryModel() }
        emit(Resource.Success(data = response))
    }.catch {
        emit(Resource.Error("An unexpected error happened, please try again..."))
    }
}