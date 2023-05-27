package dev.subfly.kmmeal.android.common.components.mealDetailShared.header

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MealDetailHeaderImage(
    imageUrl: String
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Meal Image",
        contentScale = ContentScale.Crop,
        filterQuality = FilterQuality.High,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp
            )
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
    )
}