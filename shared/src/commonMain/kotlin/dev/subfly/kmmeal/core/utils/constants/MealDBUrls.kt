package dev.subfly.kmmeal.core.utils.constants

object MealDBUrls {

    private const val THE_MEAL_DB_URL   = "https://www.themealdb.com/api/json/v1/1"
    private const val FILTER_URL        = "$THE_MEAL_DB_URL/filter.php"

    const val SEARCH_URL                = "$THE_MEAL_DB_URL/search.php?s="
    const val LOOKUP_DETAIL             = "$THE_MEAL_DB_URL/lookup.php?i="
    const val RANDOM_MEAL               = "$THE_MEAL_DB_URL/random.php"
    const val CATEGORIES                = "$THE_MEAL_DB_URL/categories.php"
    const val FILTER_INGREDIENT         = "$FILTER_URL?i="
    const val FILTER_CATEGORY           = "$FILTER_URL?c="
    const val FILTER_AREA               = "$FILTER_URL?a="

    const val INGREDIENT_IMAGE_URL      = "https://www.themealdb.com/images/ingredients"

}