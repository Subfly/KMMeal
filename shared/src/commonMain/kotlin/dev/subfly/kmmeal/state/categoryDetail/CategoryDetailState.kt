package dev.subfly.kmmeal.state.categoryDetail

import dev.subfly.kmmeal.core.base.BaseState
import dev.subfly.kmmeal.domain.model.MealModel

data class CategoryDetailState(
    override val isLoading: Boolean = true,
    override val error: String = "",
    override val data: List<MealModel> = emptyList()
): BaseState<List<MealModel>>
