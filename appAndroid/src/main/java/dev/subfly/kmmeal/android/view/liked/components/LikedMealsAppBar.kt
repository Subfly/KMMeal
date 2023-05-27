package dev.subfly.kmmeal.android.view.liked.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.subfly.kmmeal.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikedMealsAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onDeleteAllPressed: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Liked Meals",
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
                    contentDescription = "Back to Categories"
                )
            }
        },
        actions = {
            IconButton(
                onClick = onDeleteAllPressed
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Liked Meals"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}