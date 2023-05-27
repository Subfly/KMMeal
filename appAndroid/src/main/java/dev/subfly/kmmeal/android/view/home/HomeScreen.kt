package dev.subfly.kmmeal.android.view.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import dev.subfly.kmmeal.android.app.LocalGlobalNavigator
import dev.subfly.kmmeal.android.common.components.layout.BaseScreenLayout
import dev.subfly.kmmeal.android.common.components.layout.RefreshableLayout
import dev.subfly.kmmeal.android.common.components.tiles.CategoryTile
import dev.subfly.kmmeal.android.common.utils.extensions.navigate
import dev.subfly.kmmeal.android.navigation.destinations.Detail
import dev.subfly.kmmeal.android.navigation.destinations.Liked
import dev.subfly.kmmeal.android.navigation.destinations.RandomMeal
import dev.subfly.kmmeal.android.view.home.components.GetRandomMealFAB
import dev.subfly.kmmeal.android.view.home.components.HomeAppBar
import dev.subfly.kmmeal.android.view.search.SearchScreen
import dev.subfly.kmmeal.state.categories.CategoriesEvent
import dev.subfly.kmmeal.state.categories.CategoriesStateMachine
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    stateMachine: CategoriesStateMachine = koinViewModel()
) {
    val controller = LocalGlobalNavigator.current

    val uiState by stateMachine.uiState.collectAsState()

    val scrollState = TopAppBarDefaults.pinnedScrollBehavior(
        state = rememberTopAppBarState()
    )
    var isSearchActive by remember {
        mutableStateOf(false)
    }

    BaseScreenLayout(
        screenModifier = Modifier.nestedScroll(
            connection = scrollState.nestedScrollConnection
        ),
        errorMessage = uiState.error,
        isLoading = uiState.isLoading,
        topAppBar = {
            Box {
                HomeAppBar(
                    scrollBehavior = scrollState,
                    onLikedMealsPressed = {
                        controller.navigate(Liked)
                    },
                    onSearchPressed = {
                        isSearchActive = true
                    }
                )
                AnimatedVisibility(
                    visible = isSearchActive,
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically() + fadeOut()
                ) {
                    SearchScreen(
                        isSearchActive = isSearchActive,
                        onActiveChanged = { value ->
                            isSearchActive = value
                        }
                    )
                }
            }
        },
        fab = {
            GetRandomMealFAB {
                controller.navigate(RandomMeal)
            }
        },
        onErrorRetry = {
            stateMachine.onEvent(
                CategoriesEvent.Refresh
            )
        },
        onSuccessLayout = {
            RefreshableLayout(
                isRefreshing = uiState.isRefreshing,
                onRefresh = {
                    stateMachine.onEvent(
                        CategoriesEvent.Refresh
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    itemsIndexed(
                        items = uiState.data,
                        key = { _, model -> model.id }
                    ) { index, model ->
                        CategoryTile(
                            model = model,
                            modifier = Modifier.padding(vertical = 4.dp),
                            onPressed = {
                                if(model.name.isNotEmpty()) {
                                    controller.navigate(
                                        Detail.Category.passArguments(
                                            categoryName = model.name
                                        )
                                    )
                                }
                            }
                        )
                        if(index == uiState.data.lastIndex) {
                            Spacer(modifier = Modifier.padding(top = 72.dp))
                        }
                    }
                }
            }
        }
    )
}