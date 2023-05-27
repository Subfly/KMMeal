package dev.subfly.kmmeal.state.liked

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import dev.subfly.kmmeal.domain.model.MealModel
import dev.subfly.kmmeal.domain.useCase.DeleteAllLikedMeals
import dev.subfly.kmmeal.domain.useCase.GetAllLikedMeals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LikedMealsStateMachine: KMMViewModel(), KoinComponent {

    // Injections
    private val getAllLikedMealsUseCase by inject<GetAllLikedMeals>()
    private val deleteAllLikedMealsUseCase by inject<DeleteAllLikedMeals>()

    // State
    private val _likedMeals = MutableStateFlow<Set<MealModel>>(
        viewModelScope = viewModelScope,
        value = emptySet()
    )
    private val _likedMealIds = MutableStateFlow<Set<String>>(
        viewModelScope = viewModelScope,
        value = emptySet()
    )

    @NativeCoroutinesState
    val likedMeals: StateFlow<Set<MealModel>> = _likedMeals.asStateFlow()
    @NativeCoroutinesState
    val likedMealIds: StateFlow<Set<String>> = _likedMealIds.asStateFlow()

    // Init
    init {
        viewModelScope.coroutineScope.launch {
            getAllLikedMealsUseCase().collect { likedMeals ->
                val ids = likedMeals.map { it.id }
                _likedMeals.value = withContext(Dispatchers.Main) {
                    likedMeals.reversed().toSet()
                }
                _likedMealIds.value = withContext(Dispatchers.Main) {
                    ids.toSet()
                }
            }
        }
    }

    fun onEvent(event: LikedMealsEvent) {
        when(event) {
            LikedMealsEvent.DeleteAll -> {
                viewModelScope.coroutineScope.launch {
                    deleteAllLikedMealsUseCase()
                }
            }
        }
    }
}