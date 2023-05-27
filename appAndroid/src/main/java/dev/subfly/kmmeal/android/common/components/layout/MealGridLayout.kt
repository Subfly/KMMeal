package dev.subfly.kmmeal.android.common.components.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.subfly.kmmeal.android.common.components.tiles.MealTile
import dev.subfly.kmmeal.domain.model.MealModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealGridLayout(
    meals: List<MealModel>,
    onMealPressed: (String) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(meals) { model ->
            MealTile(
                model = model,
                onCardPressed = {
                    if(model.id.isNotEmpty()) {
                        onMealPressed(model.id)
                    }
                }
            )
        }
    }
}