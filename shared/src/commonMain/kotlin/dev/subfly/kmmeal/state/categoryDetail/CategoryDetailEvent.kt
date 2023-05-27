package dev.subfly.kmmeal.state.categoryDetail

sealed class CategoryDetailEvent {
    data class InitWithCategoryName(val categoryName: String): CategoryDetailEvent()
    data class Refresh(val categoryName: String): CategoryDetailEvent()
}
