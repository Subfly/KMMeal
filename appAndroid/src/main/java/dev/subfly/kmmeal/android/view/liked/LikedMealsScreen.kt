package dev.subfly.kmmeal.android.view.liked

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.subfly.kmmeal.android.R
import dev.subfly.kmmeal.android.app.LocalGlobalNavigator
import dev.subfly.kmmeal.android.app.LocalLikedMealsProvider
import dev.subfly.kmmeal.android.common.components.layout.MealGridLayout
import dev.subfly.kmmeal.android.navigation.destinations.Detail
import dev.subfly.kmmeal.android.view.liked.components.LikedMealsAppBar
import dev.subfly.kmmeal.state.liked.LikedMealsEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikedMealScreen() {

    val controller = LocalGlobalNavigator.current
    val likedMealsProvider = LocalLikedMealsProvider.current

    val likedMeals by likedMealsProvider.likedMeals.collectAsState()

    val scrollState = TopAppBarDefaults.pinnedScrollBehavior(
        state = rememberTopAppBarState()
    )

    var showDeleteAlert by remember {
        mutableStateOf(false)
    }

    val errorStringToShow = buildAnnotatedString {
        val firstPart = "It seems you have not liked any meals yet!"
        append(firstPart)
        addStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Medium,
            ),
            start = 0,
            end = firstPart.length - 1
        )

        val secondPart = "\n\nStart by pressing the heart icon on the meal detail screen."
        append(secondPart)
        addStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            ),
            start = firstPart.length,
            end = (firstPart + secondPart).length - 1
        )
    }

    if(showDeleteAlert) {
        AlertDialog(
            onDismissRequest = {
                showDeleteAlert = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        likedMealsProvider.onEvent(
                            event = LikedMealsEvent.DeleteAll
                        )
                        showDeleteAlert = false
                    }
                ) {
                    Text("I'm sure")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteAlert = false
                    }
                ) {
                    Text("Cancel")
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Liked Meals"
                )
            },
            title = {
                Text(text = "Delete All")
            },
            text = {
                Text(
                    text = "Are you sure you want to delete all liked meals? This process can not be undone!"
                )
            },
        )
    }

    Scaffold(
        modifier = Modifier.nestedScroll(
            connection = scrollState.nestedScrollConnection
        ),
        topBar = {
            LikedMealsAppBar(
                scrollBehavior = scrollState,
                onDeleteAllPressed = {
                    showDeleteAlert = true
                },
                onBackPressed = {
                    controller.popBackStack()
                }
            )
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
            contentAlignment = Alignment.Center
        ) {
            if(likedMeals.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorites Icon",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(128.dp)
                    )
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Text(
                        text = errorStringToShow,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 64.dp)
                    )
                }
            } else {
                MealGridLayout(
                    meals = likedMeals.toList(),
                    onMealPressed = { mealId ->
                        controller.navigate(
                            Detail.Meal.passArguments(
                                mealId = mealId
                            )
                        )
                    }
                )
            }
        }
    }
}