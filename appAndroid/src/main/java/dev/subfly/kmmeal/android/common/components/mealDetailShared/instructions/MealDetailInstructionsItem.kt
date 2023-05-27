package dev.subfly.kmmeal.android.common.components.mealDetailShared.instructions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun MealDetailInstructionsItem(
    instruction: String,
    instructionStep: Int
) {
    var hasCompletedInstruction by remember {
        mutableStateOf(false)
    }
    ListItem(
        headlineContent = {
            Text(text = instruction)
        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${instructionStep + 1}",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
        },
        trailingContent = {
            Checkbox(
                checked = hasCompletedInstruction,
                onCheckedChange = { value ->
                    hasCompletedInstruction = value
                }
            )
        }
    )
}