package dev.subfly.kmmeal.state.randomMeal

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import dev.subfly.kmmeal.core.model.Resource
import dev.subfly.kmmeal.core.utils.enums.LikedStatus
import dev.subfly.kmmeal.domain.useCase.GetRandomMeal
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

class RandomMealStateMachine: KMMViewModel(), KoinComponent {

    // Injections
    private val getRandomMealUseCase by inject<GetRandomMeal>()
    private val likeMealUseCase by inject<LikeMeal>()
    private val removeLikedMealUseCase by inject<RemoveLikedMeal>()

    // Jobs
    private var getRandomMealJob: Job? = null

    // State
    private val _uiState = MutableStateFlow(
        viewModelScope = viewModelScope,
        value = RandomMealUIState()
    )

    @NativeCoroutinesState
    val uiState: StateFlow<RandomMealUIState> = _uiState.asStateFlow()

    // Init
    init {
        getRandomMeal()
    }

    // Event
    fun onEvent(event: RandomMealEvent) {
        when(event) {
            is RandomMealEvent.UpdateLikedMealStatus -> {
                updateMealStatus(event.currentStatus)
            }
            RandomMealEvent.ReRoll -> getRandomMeal()
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

    private fun getRandomMeal() {
        getRandomMealJob?.cancel()
        getRandomMealJob = viewModelScope.coroutineScope.launch {
            getRandomMealUseCase().collectLatest { resource ->
                when(resource) {
                    is Resource.Error -> {
                        _uiState.value = withContext(Dispatchers.Main) {
                            _uiState.value.copy(
                                error = resource.message,
                                isLoading = false,
                                data = null
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
                                error = "",
                                isLoading = false,
                                data = resource.data
                            )
                        }
                    }
                }
            }
        }
    }

}