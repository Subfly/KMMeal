package dev.subfly.kmmeal.android.common.components.mealDetailShared.ingredients

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.subfly.kmmeal.domain.model.IngredientModel

@Composable
fun MealDetailIngredientsItem(
    ingredientModel: IngredientModel
) {
    var hasOwnedIngredient by remember {
        mutableStateOf(false)
    }
    ListItem(
        headlineContent = {
            Text(text = ingredientModel.name)
        },
        leadingContent = {
            AsyncImage(
                model = ingredientModel.imageUrl,
                contentDescription = "Ingredient Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
        },
        supportingContent = {
            Text(text = ingredientModel.count)
        },
        trailingContent = {
            Checkbox(
                checked = hasOwnedIngredient,
                onCheckedChange = { value ->
                    hasOwnedIngredient = value
                }
            )
        }
    )
}