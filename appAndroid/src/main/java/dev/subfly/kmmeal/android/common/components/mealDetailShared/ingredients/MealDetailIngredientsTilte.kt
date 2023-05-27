package dev.subfly.kmmeal.android.common.components.mealDetailShared.ingredients

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.subfly.kmmeal.android.R

@Composable
fun MealDetailIngredientsTitle() {
    Column {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text = "Ingredients",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Divider()
        ListItem(
            headlineContent = {
                Text(
                    text = "Ingredient",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            },
            leadingContent = {
                Box(
                    modifier = Modifier.size(56.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.meal_image_descriptor),
                        contentDescription = "Image Descriptor",
                    )
                }
            },
            trailingContent = {
                Text(
                    text = "Check",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        )
        Divider()
    }
}