package dev.subfly.kmmeal.state.categories

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import dev.subfly.kmmeal.core.model.Resource
import dev.subfly.kmmeal.domain.useCase.FetchCategories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CategoriesStateMachine : KMMViewModel(), KoinComponent {

    // Injection
    private val getCategoriesUseCase by inject<FetchCategories>()

    // Jobs
    private var fetchCategoriesJob: Job? = null

    // State
    private val _uiState = MutableStateFlow(
        viewModelScope = viewModelScope,
        value = CategoriesState()
    )

    @NativeCoroutinesState
    val uiState: StateFlow<CategoriesState> = _uiState.asStateFlow()

    fun onEvent(event: CategoriesEvent) {
        when (event) {
            CategoriesEvent.Refresh -> getCategories()
        }
    }

    // Init
    init {
        getCategories()
    }

    private fun getCategories() {
        fetchCategoriesJob?.cancel()
        fetchCategoriesJob = viewModelScope.coroutineScope.launch {
            getCategoriesUseCase().collectLatest { resource ->
                when (resource) {
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

                    is Resource.Error -> {
                        _uiState.value = withContext(Dispatchers.Main) {
                            _uiState.value.copy(
                                isLoading = false,
                                error = resource.message,
                                data = emptyList()
                            )
                        }
                    }
                }
            }
        }
    }
}