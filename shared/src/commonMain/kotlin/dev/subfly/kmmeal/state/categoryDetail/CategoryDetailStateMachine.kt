package dev.subfly.kmmeal.state.categoryDetail

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import dev.subfly.kmmeal.core.model.Resource
import dev.subfly.kmmeal.core.utils.enums.SearchFilterType
import dev.subfly.kmmeal.domain.useCase.SearchMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CategoryDetailStateMachine: KMMViewModel(), KoinComponent {

    // Injections
    private val getMealsOfCategoryUseCase by inject<SearchMeal>()

    // Jobs
    private var fetchMealsJob: Job? = null

    // State
    private val _uiState = MutableStateFlow(
        viewModelScope = viewModelScope,
        value = CategoryDetailState()
    )

    @NativeCoroutinesState
    val uiState: StateFlow<CategoryDetailState> = _uiState.asStateFlow()

    // Event
    fun onEvent(event: CategoryDetailEvent) {
        when(event) {
            is CategoryDetailEvent.InitWithCategoryName -> {
                fetchMeals(categoryName = event.categoryName)
            }
            is CategoryDetailEvent.Refresh -> {
                fetchMeals(categoryName = event.categoryName)
            }
        }
    }

    // Private Functions
    private fun fetchMeals(categoryName: String) {
        fetchMealsJob?.cancel()
        fetchMealsJob = viewModelScope.coroutineScope.launch {
            getMealsOfCategoryUseCase(
                by = SearchFilterType.CATEGORY,
                query = categoryName
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
                                error = ""
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
    }
}