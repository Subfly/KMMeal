package dev.subfly.kmmeal.state.search

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import dev.subfly.kmmeal.core.model.Resource
import dev.subfly.kmmeal.core.utils.enums.SearchFilterType
import dev.subfly.kmmeal.domain.useCase.SearchMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(FlowPreview::class)
class SearchStateMachine: KMMViewModel(), KoinComponent {

    // Injections
    private val searchMealUseCase by inject<SearchMeal>()

    // State
    private val _uiState = MutableStateFlow(
        viewModelScope = viewModelScope,
        value = SearchUIState()
    )
    @NativeCoroutinesState
    val uiState: StateFlow<SearchUIState> = _uiState.asStateFlow()

    private val _queryState = MutableStateFlow(
        viewModelScope = viewModelScope,
        value = ""
    )
    @NativeCoroutinesState
    val queryState: StateFlow<String> = _queryState.asStateFlow()

    private val _filterState = MutableStateFlow(
        viewModelScope = viewModelScope,
        value = SearchFilterType.NONE
    )
    @NativeCoroutinesState
    val filterState: StateFlow<SearchFilterType> = _filterState.asStateFlow()

    // Init
    init {
        combine(
            filterState,
            queryState.debounce(250)
        ) { currentFilter, currentQuery ->
            if(currentQuery.isEmpty()) {
                _uiState.value = withContext(Dispatchers.Main) {
                    _uiState.value.copy(
                        isLoading = false,
                        error = "",
                        data = emptyList()
                    )
                }
            } else {
                searchMealUseCase(
                    by = currentFilter,
                    query = currentQuery
                ).collectLatest { resource ->
                    when(resource) {
                        is Resource.Error -> {
                            _uiState.value = withContext(Dispatchers.Main) {
                                _uiState.value.copy(
                                    isLoading = false,
                                    error = resource.message,
                                    data = emptyList()
                                )
                            }
                        }
                        Resource.Loading -> {
                            _uiState.value = withContext(Dispatchers.Main) {
                                _uiState.value.copy(
                                    isLoading = true,
                                    error = "",
                                    data = emptyList()
                                )
                            }
                        }
                        is Resource.Success -> {
                            _uiState.value = withContext(Dispatchers.Main) {
                                _uiState.value.copy(
                                    isLoading = false,
                                    error = "",
                                    data = resource.data
                                )
                            }
                        }
                    }
                }
            }
        }.launchIn(viewModelScope.coroutineScope)
    }

    // Events
    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.OnFilterChanged -> {
                _filterState.value = event.newFilter
            }
            is SearchEvent.OnQueryChanged -> {
                _queryState.value = event.newQuery
            }
        }
    }
}