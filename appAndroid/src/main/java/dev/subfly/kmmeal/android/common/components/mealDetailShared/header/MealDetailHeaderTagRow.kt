package dev.subfly.kmmeal.android.common.components.mealDetailShared.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.subfly.kmmeal.android.R

@OptIn(
    ExperimentalLayoutApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun MealDetailHeaderTagRow(
    mealTags: List<String>
) {
    FlowRow(
        maxItemsInEachRow = 8,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.step_indicator),
                contentDescription = "Tags",
                tint = Color.White
            )
        }
        mealTags.forEachIndexed { index, tag ->
            ElevatedSuggestionChip(
                onClick = {  },
                label = {
                    Text(text = tag)
                },
                enabled = true,
                modifier = Modifier.composed {
                    if(index == 0)
                        Modifier.padding(horizontal = 8.dp)
                    else
                        Modifier.padding(end = 8.dp)
                }
            )
        }
    }
}