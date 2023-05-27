package dev.subfly.kmmeal.android.common.components.mealDetailShared.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.subfly.kmmeal.android.R

@Composable
fun MealDetailHeader(
    imageUrl: String,
    mealName: String,
    mealArea: String,
    mealCategory: String,
    mealTags: List<String>
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MealDetailHeaderImage(
            imageUrl = imageUrl
        )
        Text(
            text = mealName,
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            MealDetailHeaderSpecificator(
                title = "Area",
                icon = R.drawable.area_icon,
                specification = mealArea
            )
            MealDetailHeaderSpecificator(
                title = "Category",
                icon = R.drawable.category_icon,
                specification = mealCategory
            )
        }
        if(mealTags.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(top = 8.dp))
            MealDetailHeaderTagRow(
                mealTags = mealTags
            )
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Divider()
    }
}