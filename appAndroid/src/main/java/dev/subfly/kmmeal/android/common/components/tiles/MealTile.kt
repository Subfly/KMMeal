package dev.subfly.kmmeal.android.common.components.tiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import dev.subfly.kmmeal.domain.model.MealModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealTile(
    model: MealModel,
    onCardPressed: () -> Unit = {}
) {
    var canImageLoad by remember {
        mutableStateOf(true)
    }

    Card(
        onClick = onCardPressed
    ) {
        if(canImageLoad) {
            SubcomposeAsyncImage(
                model = model.imageUrl,
                contentDescription = "Meal Image",
                success = {
                    Image(
                        painter = it.painter,
                        contentDescription = "Meal Image",
                        modifier = Modifier.fillMaxSize()
                    )
                },
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                },
                error = {
                    canImageLoad = false
                },
            )
        }
        Text(
            text = model.name,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .padding(8.dp)
        )
    }
}