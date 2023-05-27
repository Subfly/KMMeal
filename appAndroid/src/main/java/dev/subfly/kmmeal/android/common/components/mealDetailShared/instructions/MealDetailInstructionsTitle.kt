package dev.subfly.kmmeal.android.common.components.mealDetailShared.instructions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MealDetailInstructionsTitle() {
    Column {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Divider()
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text = "Instructions",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Divider()
        Spacer(modifier = Modifier.padding(top = 16.dp))
    }
}