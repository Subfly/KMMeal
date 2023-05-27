package dev.subfly.kmmeal.domain.useCase

import dev.subfly.kmmeal.core.model.Resource
import dev.subfly.kmmeal.data.remote.repository.MealRepository
import dev.subfly.kmmeal.domain.mappers.toMealModel
import dev.subfly.kmmeal.domain.model.MealModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetMealDetail(
    private val repository: MealRepository
) {
    suspend operator fun invoke(
        mealId: String
    ): Flow<Resource<MealModel>> = flow {
        emit(Resource.Loading)
        val response = repository
            .lookupMealDetailById(mealId)
            .toMealModel()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error("An unexpected error happened, please try again..."))
    }
}