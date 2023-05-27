package dev.subfly.kmmeal.domain.mappers

import dev.subfly.kmmeal.data.remote.dto.CategoryDTO
import dev.subfly.kmmeal.domain.model.CategoryModel

fun CategoryDTO.toCategoryModel(): CategoryModel {
    if (idCategory.isNullOrEmpty())
        throw NullPointerException()

    return CategoryModel(
        id = idCategory,
        name = strCategory.orEmpty(),
        description = strCategoryDescription.orEmpty(),
        imageUrl = strCategoryThumb.orEmpty()
    )
}