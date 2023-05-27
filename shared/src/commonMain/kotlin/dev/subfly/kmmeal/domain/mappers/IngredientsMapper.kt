package dev.subfly.kmmeal.domain.mappers

import dev.subfly.kmmeal.data.local.entities.IngredientEntity
import dev.subfly.kmmeal.domain.model.IngredientModel

fun IngredientEntity.toIngredientsModel(): IngredientModel {
    return IngredientModel(
        name = name,
        count = count,
        imageUrl = imageUrl
    )
}

fun IngredientModel.toIngredientEntity(): IngredientEntity {
    val modelName = name
    val modelCount = count
    val modelImageUrl = imageUrl

    return IngredientEntity().apply {
        name = modelName
        count = modelCount
        imageUrl = modelImageUrl
    }
}