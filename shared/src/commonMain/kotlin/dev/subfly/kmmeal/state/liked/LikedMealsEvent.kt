package dev.subfly.kmmeal.state.liked

sealed class LikedMealsEvent {
    object DeleteAll: LikedMealsEvent()
}
