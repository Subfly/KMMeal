package dev.subfly.kmmeal.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val idCategory: String?,
    val strCategory: String?,
    val strCategoryDescription: String?,
    val strCategoryThumb: String?
)

@Serializable
data class CategoryResponse(
    val categories: List<CategoryDTO>? = emptyList()
)