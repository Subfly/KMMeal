package dev.subfly.kmmeal.state.randomMeal

import dev.subfly.kmmeal.core.utils.enums.LikedStatus

sealed class RandomMealEvent {
    data class UpdateLikedMealStatus(val currentStatus: LikedStatus): RandomMealEvent()
    object ReRoll: RandomMealEvent()
}
