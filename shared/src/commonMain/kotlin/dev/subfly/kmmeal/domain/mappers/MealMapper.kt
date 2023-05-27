package dev.subfly.kmmeal.domain.mappers

import dev.subfly.kmmeal.core.utils.constants.MealDBUrls
import dev.subfly.kmmeal.core.utils.extensions.cleanTextContent
import dev.subfly.kmmeal.data.local.entities.LikedMealEntity
import dev.subfly.kmmeal.data.remote.dto.MealDTO
import dev.subfly.kmmeal.domain.model.IngredientModel
import dev.subfly.kmmeal.domain.model.MealModel
import io.realm.kotlin.ext.toRealmList

fun MealDTO.toMealModel(): MealModel {
    if(idMeal.isNullOrEmpty())
        throw NullPointerException()

    // This is one of the worst codes I have wrote, anyway...
    val ingredients = mutableListOf<IngredientModel>()

    fun generateIngredient(name: String, count: String) {
        if(name.isNotEmpty() && count.isNotEmpty()) {
            val nameFormatted = name.replace(" ", "%20")
            ingredients.add(
                IngredientModel(
                    name = name,
                    count = count,
                    imageUrl = "${MealDBUrls.INGREDIENT_IMAGE_URL}/$nameFormatted.png"
                )
            )
        }
    }

    if(strIngredient1 != null && strMeasure1 != null) {
        generateIngredient(
            name = strIngredient1,
            count = strMeasure1
        )
    }

    if(strIngredient2 != null && strMeasure2 != null) {
        generateIngredient(
            name = strIngredient2,
            count = strMeasure2
        )
    }

    if(strIngredient3 != null && strMeasure3 != null) {
        generateIngredient(
            name = strIngredient3,
            count = strMeasure3
        )
    }

    if(strIngredient4 != null && strMeasure4 != null) {
        generateIngredient(
            name = strIngredient4,
            count = strMeasure4
        )
    }

    if(strIngredient5 != null && strMeasure5 != null) {
        generateIngredient(
            name = strIngredient5,
            count = strMeasure5
        )
    }

    if(strIngredient6 != null && strMeasure6 != null) {
        generateIngredient(
            name = strIngredient6,
            count = strMeasure6
        )
    }

    if(strIngredient7 != null && strMeasure7 != null) {
        generateIngredient(
            name = strIngredient7,
            count = strMeasure7
        )
    }

    if(strIngredient8 != null && strMeasure8 != null) {
        generateIngredient(
            name = strIngredient8,
            count = strMeasure8
        )
    }

    if(strIngredient9 != null && strMeasure9 != null) {
        generateIngredient(
            name = strIngredient9,
            count = strMeasure9
        )
    }

    if(strIngredient10 != null && strMeasure10 != null) {
        generateIngredient(
            name = strIngredient10,
            count = strMeasure10
        )
    }

    if(strIngredient11 != null && strMeasure11 != null) {
        generateIngredient(
            name = strIngredient11,
            count = strMeasure11
        )
    }

    if(strIngredient12 != null && strMeasure12 != null) {
        generateIngredient(
            name = strIngredient12,
            count = strMeasure12
        )
    }

    if(strIngredient13 != null && strMeasure13 != null) {
        generateIngredient(
            name = strIngredient13,
            count = strMeasure13
        )
    }

    if(strIngredient14 != null && strMeasure14 != null) {
        generateIngredient(
            name = strIngredient14,
            count = strMeasure14
        )
    }

    if(strIngredient15 != null && strMeasure15 != null) {
        generateIngredient(
            name = strIngredient15,
            count = strMeasure15
        )
    }

    if(strIngredient16 != null && strMeasure16 != null) {
        generateIngredient(
            name = strIngredient16,
            count = strMeasure16
        )
    }

    if(strIngredient17 != null && strMeasure17 != null) {
        generateIngredient(
            name = strIngredient17,
            count = strMeasure17
        )
    }

    if(strIngredient18 != null && strMeasure18 != null) {
        generateIngredient(
            name = strIngredient18,
            count = strMeasure18
        )
    }

    if(strIngredient19 != null && strMeasure19 != null) {
        generateIngredient(
            name = strIngredient19,
            count = strMeasure19
        )
    }

    if(strIngredient20 != null && strMeasure20 != null) {
        generateIngredient(
            name = strIngredient20,
            count = strMeasure20
        )
    }


    val tags = mutableListOf<String>()
    if(!strTags.isNullOrEmpty()) {
        tags.addAll(strTags.split(","))
    }

    val instructions = mutableListOf<String>()
    if(!strInstructions.isNullOrEmpty()) {
        val split = strInstructions.split("\r")
        val cleaned = split
            .map { it.cleanTextContent }
            .filter { it.isNotEmpty() || it.contains("STEP", ignoreCase = false) }
        instructions.addAll(cleaned)
    }

    return MealModel(
        id = idMeal,
        name = strMeal.orEmpty(),
        area = strArea.orEmpty(),
        category = strCategory.orEmpty(),
        imageUrl = strMealThumb.orEmpty(),
        drinkAlternative = strDrinkAlternate.orEmpty(),
        ingredients = ingredients,
        instructions = instructions,
        tags = tags,
        videoUrl = strYoutube.orEmpty()
    )
}

fun LikedMealEntity.toMealModel(): MealModel {
    return MealModel(
        id = id,
        name = name,
        area = area,
        category = category,
        imageUrl = imageUrl,
        drinkAlternative = drinkAlternative,
        ingredients = ingredients.map { it.toIngredientsModel() },
        instructions = instructions,
        tags = tags,
        videoUrl = videoUrl
    )
}

fun MealModel.toLikedMealEntity(): LikedMealEntity {
    val modelId = id
    val modelName = name
    val modelArea = area
    val modelCategory = category
    val modelImageUrl = imageUrl
    val modelDrinkAlternative = drinkAlternative
    val modelIngredients = ingredients.map { it.toIngredientEntity() }
    val modelInstructions = instructions
    val modelTags = tags
    val modelVideoUrl = videoUrl

    return LikedMealEntity().apply {
        id = modelId
        name = modelName
        area = modelArea
        category = modelCategory
        imageUrl = modelImageUrl
        drinkAlternative = modelDrinkAlternative
        ingredients = modelIngredients.toRealmList()
        instructions = modelInstructions.toRealmList()
        tags = modelTags.toRealmList()
        videoUrl = modelVideoUrl
    }
}