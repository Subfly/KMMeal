package dev.subfly.kmmeal.android.view.randomMeal.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.subfly.kmmeal.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomMealAppBar(
    isLiked: Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    onLikePressed: () -> Unit,
    onReRollPressed: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Random Meal",
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
                    contentDescription = "Back to Home"
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
            IconButton(
                onClick = onReRollPressed
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.random_meal),
                    contentDescription = "Re-Roll a Random Meal"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}