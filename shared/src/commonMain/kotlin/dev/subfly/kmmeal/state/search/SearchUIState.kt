package dev.subfly.kmmeal.state.search

import dev.subfly.kmmeal.core.base.BaseState
import dev.subfly.kmmeal.domain.model.MealModel

data class SearchUIState(
    override val isLoading: Boolean = false,
    override val error: String = "",
    override val data: List<MealModel> = emptyList()
): BaseState<List<MealModel>>
