package dev.subfly.kmmeal.state.categories

sealed class CategoriesEvent {
    object Refresh: CategoriesEvent()
}
