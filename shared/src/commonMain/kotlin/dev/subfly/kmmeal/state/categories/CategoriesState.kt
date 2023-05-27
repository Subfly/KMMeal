package dev.subfly.kmmeal.state.categories

import dev.subfly.kmmeal.core.base.BaseState
import dev.subfly.kmmeal.domain.model.CategoryModel

data class CategoriesState(
    override val data: List<CategoryModel> = emptyList(),
    override val isLoading: Boolean = true,
    override val error: String = "",
    val isRefreshing: Boolean = false
): BaseState<List<CategoryModel>>
