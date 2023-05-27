package dev.subfly.kmmeal.state.mealDetail

import dev.subfly.kmmeal.core.utils.enums.LikedStatus

sealed class MealDetailEvent {
    data class InitWithMealId(val mealId: String): MealDetailEvent()
    data class UpdateLikedMealStatus(val currentStatus: LikedStatus): MealDetailEvent()
    data class Refresh(val mealId: String): MealDetailEvent()
}
