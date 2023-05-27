package dev.subfly.kmmeal.android.view.home.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import dev.subfly.kmmeal.android.R

@Composable
fun GetRandomMealFAB(
    onPressed: () -> Unit
) {
    FloatingActionButton(
        onClick = onPressed,
    ) {
        Icon(
            painterResource(id = R.drawable.random_meal),
            contentDescription = "Show a random Meal"
        )
    }
}