package dev.subfly.kmmeal.domain.useCase

import dev.subfly.kmmeal.data.local.database.LikedMealsDatabase
import dev.subfly.kmmeal.domain.mappers.toMealModel
import dev.subfly.kmmeal.domain.model.MealModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllLikedMeals(
    private val database: LikedMealsDatabase
) {
    operator fun invoke(): Flow<List<MealModel>> = flow {
        database.getAllLikedMeals().collect { listOfEntities ->
            emit(
                listOfEntities.map { entity ->
                    entity.toMealModel()
                }
            )
        }
    }
}