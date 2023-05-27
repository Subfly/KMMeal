package dev.subfly.kmmeal.android.di

import dev.subfly.kmmeal.state.categories.CategoriesStateMachine
import dev.subfly.kmmeal.state.categoryDetail.CategoryDetailStateMachine
import dev.subfly.kmmeal.state.liked.LikedMealsStateMachine
import dev.subfly.kmmeal.state.mealDetail.MealDetailStateMachine
import dev.subfly.kmmeal.state.randomMeal.RandomMealStateMachine
import dev.subfly.kmmeal.state.search.SearchStateMachine
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    viewModel { _ -> LikedMealsStateMachine() }
    viewModel { _ -> CategoriesStateMachine() }
    viewModel { _ -> CategoryDetailStateMachine() }
    viewModel { _ -> MealDetailStateMachine() }
    viewModel { _ -> RandomMealStateMachine() }
    viewModel { _ -> SearchStateMachine() }
}