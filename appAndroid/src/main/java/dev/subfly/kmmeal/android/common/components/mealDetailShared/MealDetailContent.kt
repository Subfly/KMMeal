package dev.subfly.kmmeal.android.common.components.mealDetailShared

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.subfly.kmmeal.android.common.components.mealDetailShared.header.MealDetailHeader
import dev.subfly.kmmeal.android.common.components.mealDetailShared.ingredients.MealDetailIngredientsItem
import dev.subfly.kmmeal.android.common.components.mealDetailShared.ingredients.MealDetailIngredientsTitle
import dev.subfly.kmmeal.android.common.components.mealDetailShared.instructions.MealDetailInstructionsItem
import dev.subfly.kmmeal.android.common.components.mealDetailShared.instructions.MealDetailInstructionsTitle
import dev.subfly.kmmeal.domain.model.MealModel

@Composable
fun MealDetailContent(
    model: MealModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp
            )
    ) {
        item {
            MealDetailHeader(
                imageUrl = model.imageUrl,
                mealName = model.name,
                mealArea = model.area,
                mealCategory = model.category,
                mealTags = model.tags
            )
        }
        if(model.ingredients.isNotEmpty()) {
            item {
                MealDetailIngredientsTitle()
            }
        }
        model.ingredients.forEach { ingredientModel ->
            item {
                MealDetailIngredientsItem(
                    ingredientModel = ingredientModel
                )
            }
        }
        if(model.instructions.isNotEmpty()) {
            item {
                MealDetailInstructionsTitle()
            }
        }
        model.instructions.forEachIndexed { index, instruction ->
            item {
                MealDetailInstructionsItem(
                    instruction = instruction,
                    instructionStep = index
                )
            }
        }
        item {
            Spacer(modifier = Modifier.padding(top = 8.dp))
        }
    }
}