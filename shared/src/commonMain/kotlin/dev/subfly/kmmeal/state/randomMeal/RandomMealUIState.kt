package dev.subfly.kmmeal.state.randomMeal

import dev.subfly.kmmeal.core.base.BaseState
import dev.subfly.kmmeal.domain.model.MealModel

data class RandomMealUIState(
    override val isLoading: Boolean = true,
    override val error: String = "",
    override val data: MealModel? = null
): BaseState<MealModel?>
