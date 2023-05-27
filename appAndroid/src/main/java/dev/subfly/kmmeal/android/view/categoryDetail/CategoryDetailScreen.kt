package dev.subfly.kmmeal.android.view.categoryDetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import dev.subfly.kmmeal.android.app.LocalGlobalNavigator
import dev.subfly.kmmeal.android.common.components.layout.BaseScreenLayout
import dev.subfly.kmmeal.android.common.components.layout.MealGridLayout
import dev.subfly.kmmeal.android.navigation.destinations.Detail
import dev.subfly.kmmeal.android.view.categoryDetail.components.CategoryDetailAppBar
import dev.subfly.kmmeal.state.categoryDetail.CategoryDetailEvent
import dev.subfly.kmmeal.state.categoryDetail.CategoryDetailStateMachine
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(
    categoryName: String,
    stateMachine: CategoryDetailStateMachine = koinViewModel()
) {
    val controller = LocalGlobalNavigator.current

    val uiState by stateMachine.uiState.collectAsState()

    val scrollState = TopAppBarDefaults.pinnedScrollBehavior(
        state = rememberTopAppBarState()
    )
    
    LaunchedEffect(Unit) {
        stateMachine.onEvent(
            CategoryDetailEvent.InitWithCategoryName(
                categoryName = categoryName
            )
        )
    }

    BaseScreenLayout(
        screenModifier = Modifier.nestedScroll(
            connection = scrollState.nestedScrollConnection
        ),
        topAppBar = {
            CategoryDetailAppBar(
                categoryName = categoryName,
                scrollBehavior = scrollState,
                onBackPressed = {
                    controller.popBackStack()
                }
            )
        },
        onSuccessLayout = {
            MealGridLayout(
                meals = uiState.data,
                onMealPressed = { mealId ->
                    controller.navigate(
                        Detail.Meal.passArguments(
                            mealId = mealId
                        )
                    )
                }
            )
        },
        onErrorRetry = {
            stateMachine.onEvent(
                event = CategoryDetailEvent.Refresh(
                    categoryName = categoryName
                )
            )
        },
        isLoading = uiState.isLoading,
        errorMessage = uiState.error
    )
}