package dev.subfly.kmmeal.domain.model

data class MealModel(
    val id: String,
    val name: String,
    val area: String,
    val category: String,
    val imageUrl: String,
    val drinkAlternative: String,
    val ingredients: List<IngredientModel>,
    val instructions: List<String>,
    val tags: List<String>,
    val videoUrl: String
)
