package dev.subfly.kmmeal.android.view.randomMeal

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import dev.subfly.kmmeal.android.app.LocalGlobalNavigator
import dev.subfly.kmmeal.android.app.LocalLikedMealsProvider
import dev.subfly.kmmeal.android.common.components.layout.BaseScreenLayout
import dev.subfly.kmmeal.android.common.components.mealDetailShared.MealDetailContent
import dev.subfly.kmmeal.android.view.randomMeal.components.RandomMealAppBar
import dev.subfly.kmmeal.core.utils.enums.LikedStatus
import dev.subfly.kmmeal.state.randomMeal.RandomMealEvent
import dev.subfly.kmmeal.state.randomMeal.RandomMealStateMachine
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomMealScreen(
    stateMachine: RandomMealStateMachine = koinViewModel()
) {
    val controller = LocalGlobalNavigator.current
    val likedMealsProvider = LocalLikedMealsProvider.current
    
    val uiState by stateMachine.uiState.collectAsState()
    val likedMealIds by likedMealsProvider.likedMealIds.collectAsState()

    val scrollState = TopAppBarDefaults.pinnedScrollBehavior(
        state = rememberTopAppBarState()
    )

    BaseScreenLayout(
        screenModifier = Modifier.nestedScroll(
            connection = scrollState.nestedScrollConnection
        ),
        topAppBar = {
            RandomMealAppBar(
                isLiked = uiState.data?.id in likedMealIds,
                scrollBehavior = scrollState,
                onLikePressed = {
                    val isLiked = uiState.data?.id in likedMealIds
                    stateMachine.onEvent(
                        event = RandomMealEvent.UpdateLikedMealStatus(
                            currentStatus = if(isLiked) LikedStatus.LIKED else LikedStatus.NOT_LIKED
                        )
                    )
                },
                onReRollPressed = {
                    stateMachine.onEvent(
                        RandomMealEvent.ReRoll
                    )
                },
                onBackPressed = {
                    controller.popBackStack()
                }
            )
        },
        onErrorRetry = {
            stateMachine.onEvent(
                RandomMealEvent.ReRoll
            )
        },
        onSuccessLayout = {
            uiState.data?.let { model ->
                MealDetailContent(model = model)
            }
        },
        isLoading = uiState.isLoading,
        errorMessage = uiState.error
    )
}