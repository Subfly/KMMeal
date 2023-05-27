package dev.subfly.kmmeal.android.common.components.mealDetailShared.header

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MealDetailHeaderSpecificator(
    title: String,
    @DrawableRes icon: Int,
    specification: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Area Indicator"
            )
            Text(
                text = title.ifEmpty { "-" },
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            text = specification,
            style = TextStyle(
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic
            )
        )
    }
}