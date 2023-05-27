package dev.subfly.kmmeal.android.view.mealDetail

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
import dev.subfly.kmmeal.android.app.LocalLikedMealsProvider
import dev.subfly.kmmeal.android.common.components.layout.BaseScreenLayout
import dev.subfly.kmmeal.android.common.components.mealDetailShared.MealDetailContent
import dev.subfly.kmmeal.android.view.mealDetail.components.MealDetailAppBar
import dev.subfly.kmmeal.core.utils.enums.LikedStatus
import dev.subfly.kmmeal.state.mealDetail.MealDetailEvent
import dev.subfly.kmmeal.state.mealDetail.MealDetailStateMachine
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealDetailScreen(
    mealId: String,
    stateMachine: MealDetailStateMachine = koinViewModel()
) {
    val controller = LocalGlobalNavigator.current
    val likedMealsProvider = LocalLikedMealsProvider.current

    val uiState by stateMachine.uiState.collectAsState()
    val likedMealIds by likedMealsProvider.likedMealIds.collectAsState()

    val scrollState = TopAppBarDefaults.pinnedScrollBehavior(
        state = rememberTopAppBarState()
    )

    LaunchedEffect(Unit) {
        stateMachine.onEvent(
            MealDetailEvent.InitWithMealId(
                mealId = mealId
            )
        )
    }

    BaseScreenLayout(
        screenModifier = Modifier.nestedScroll(
            connection = scrollState.nestedScrollConnection
        ),
        topAppBar = {
            MealDetailAppBar(
                isLiked = mealId in likedMealIds,
                contentDescriptionCategory = uiState.data?.category ?: "",
                scrollBehavior = scrollState,
                onBackPressed = {
                    controller.popBackStack()
                },
                onLikePressed = {
                    stateMachine.onEvent(
                        event = MealDetailEvent.UpdateLikedMealStatus(
                            currentStatus = if(mealId in likedMealIds) LikedStatus.LIKED else LikedStatus.NOT_LIKED
                        )
                    )
                }
            )
        },
        onErrorRetry = {
            stateMachine.onEvent(
                event = MealDetailEvent.Refresh(
                    mealId = mealId
                )
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