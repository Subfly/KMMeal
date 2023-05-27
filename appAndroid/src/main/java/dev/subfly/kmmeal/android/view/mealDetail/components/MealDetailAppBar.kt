package dev.subfly.kmmeal.android.view.mealDetail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealDetailAppBar(
    isLiked: Boolean,
    contentDescriptionCategory: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackPressed: () -> Unit,
    onLikePressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Meal Detail",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Meals of $contentDescriptionCategory"
                )
            }
        },
        actions = {
            IconButton(
                onClick = onLikePressed
            ) {
                Icon(
                    imageVector = if(isLiked)
                        Icons.Outlined.Favorite
                    else
                        Icons.Outlined.FavoriteBorder,
                    contentDescription = "Like Meal",
                    tint = if(isLiked) Color.Red else LocalContentColor.current
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}