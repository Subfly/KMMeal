package dev.subfly.kmmeal.android.view.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.subfly.kmmeal.android.R
import dev.subfly.kmmeal.android.app.LocalGlobalNavigator
import dev.subfly.kmmeal.android.common.components.layout.MealGridLayout
import dev.subfly.kmmeal.android.navigation.destinations.Detail
import dev.subfly.kmmeal.core.utils.enums.SearchFilterType
import dev.subfly.kmmeal.state.search.SearchEvent
import dev.subfly.kmmeal.state.search.SearchStateMachine
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    isSearchActive: Boolean,
    onActiveChanged: (Boolean) -> Unit,
    stateMachine: SearchStateMachine = koinViewModel()
) {

    val controller = LocalGlobalNavigator.current

    val uiState by stateMachine.uiState.collectAsState()
    val queryState by stateMachine.queryState.collectAsState()
    val currentFilter by stateMachine.filterState.collectAsState()

    val scope = rememberCoroutineScope()
    val filterSheetState = rememberModalBottomSheetState()

    var shouldShowModal by remember {
        mutableStateOf(false)
    }

    SearchBar(
        query = queryState,
        onQueryChange = { newQuery ->
            stateMachine.onEvent(
                event = SearchEvent.OnQueryChanged(
                    newQuery = newQuery
                )
            )
        },
        active = isSearchActive,
        onActiveChange = onActiveChanged,
        onSearch = { newQuery ->
            stateMachine.onEvent(
                event = SearchEvent.OnQueryChanged(
                    newQuery = newQuery
                )
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {
                    onActiveChanged(false)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Close Search"
                )
            }
        },
        trailingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        stateMachine.onEvent(
                            event = SearchEvent.OnQueryChanged(
                                newQuery = ""
                            )
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Searched Query"
                    )
                }
                IconButton(
                    onClick = {
                        scope.launch {
                            shouldShowModal = true
                            filterSheetState.expand()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter_icon),
                        contentDescription = "Change Filter"
                    )
                }
            }
        },
        placeholder = {
            Text(text = "Search in meals")
        }
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator()
                uiState.error.isNotEmpty() -> Text(
                    text = "An error happened, please try again",
                    textAlign = TextAlign.Center
                )
                uiState.data.isEmpty() && queryState.isNotEmpty() -> {
                    val textToShow = buildAnnotatedString {
                        val firstPart = "Nothing found containing "
                        append(firstPart)

                        val queryToAppend ="'$queryState'"
                        append(queryToAppend)
                        addStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Medium
                            ),
                            start = firstPart.length,
                            end = (firstPart + queryToAppend).length
                        )

                        if(currentFilter != SearchFilterType.NONE) {
                            val secondPart = " with filter "
                            append(secondPart)

                            append(currentFilter.displayName)
                            addStyle(
                                style = SpanStyle(
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Light
                                ),
                                start = (firstPart + queryToAppend + secondPart).length,
                                end = (firstPart + queryToAppend + secondPart + currentFilter.displayName).length,
                            )
                        }
                    }
                    Text(
                        text = textToShow,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 64.dp)
                    )
                }
                uiState.data.isEmpty() -> Text(
                    text = "Start typing to search meals...",
                    textAlign = TextAlign.Center
                )
                else -> {
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
                }
            }
            if(shouldShowModal) {
                ModalBottomSheet(
                    onDismissRequest = {
                        shouldShowModal = false
                    },
                    sheetState = filterSheetState
                ) {
                    SearchFilterType.values().forEach { filter ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = filter == currentFilter,
                                onClick = {
                                    stateMachine.onEvent(
                                        event = SearchEvent.OnFilterChanged(
                                            newFilter = filter
                                        )
                                    )
                                    scope.launch {
                                        filterSheetState.hide()
                                        shouldShowModal = false
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                            Text(text = filter.displayName)
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                }
            }
        }
    }
}