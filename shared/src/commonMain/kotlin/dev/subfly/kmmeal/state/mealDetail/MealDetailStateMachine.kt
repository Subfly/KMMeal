package dev.subfly.kmmeal.state.mealDetail

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import dev.subfly.kmmeal.core.model.Resource
import dev.subfly.kmmeal.core.utils.enums.LikedStatus
import dev.subfly.kmmeal.domain.useCase.GetMealDetail
import dev.subfly.kmmeal.domain.useCase.LikeMeal
import dev.subfly.kmmeal.domain.useCase.RemoveLikedMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MealDetailStateMachine: KMMViewModel(), KoinComponent {

    // Injections
    private val getMealDetailUseCase by inject<GetMealDetail>()
    private val likeMealUseCase by inject<LikeMeal>()
    private val removeLikedMealUseCase by inject<RemoveLikedMeal>()

    // Jobs
    private var fetchDetailsJob: Job? = null

    // State
    private val _uiState =  MutableStateFlow(
        viewModelScope = viewModelScope,
        value = MealDetailUIState()
    )

    @NativeCoroutinesState
    val uiState: StateFlow<MealDetailUIState> = _uiState.asStateFlow()

    // Event
    fun onEvent(event: MealDetailEvent) {
        when(event) {
            is MealDetailEvent.InitWithMealId -> {
                fetchMealDetail(event.mealId)
            }
            is MealDetailEvent.UpdateLikedMealStatus -> {
                updateMealStatus(event.currentStatus)
            }
            is MealDetailEvent.Refresh -> {
                fetchMealDetail(event.mealId)
            }
        }
    }

    // Private Functions
    private fun fetchMealDetail(mealId: String) {
        fetchDetailsJob?.cancel()
        fetchDetailsJob = viewModelScope.coroutineScope.launch {
            getMealDetailUseCase(mealId = mealId).collectLatest { resource ->
                when(resource) {
                    is Resource.Error -> {
                        _uiState.value = withContext(Dispatchers.Main) {
                            _uiState.value.copy(
                                isLoading = false,
                                error = resource.message,
                                data = null
                            )
                        }
                    }
                    Resource.Loading -> {
                        _uiState.value = withContext(Dispatchers.Main) {
                            _uiState.value.copy(
                                isLoading = true,
                                error = "",
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

    private fun updateMealStatus(status: LikedStatus) {
        val currentMeal = uiState.value.data
        if(currentMeal != null) {
            viewModelScope.coroutineScope.launch {
                when(status) {
                    LikedStatus.LIKED -> removeLikedMealUseCase(currentMeal)
                    LikedStatus.NOT_LIKED -> likeMealUseCase(currentMeal)
                }
            }
        }
    }
}