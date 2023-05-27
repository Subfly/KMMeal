package dev.subfly.kmmeal.di.modules

import dev.subfly.kmmeal.domain.useCase.DeleteAllLikedMeals
import dev.subfly.kmmeal.domain.useCase.FetchCategories
import dev.subfly.kmmeal.domain.useCase.GetAllLikedMeals
import dev.subfly.kmmeal.domain.useCase.GetLikedMeal
import dev.subfly.kmmeal.domain.useCase.GetMealDetail
import dev.subfly.kmmeal.domain.useCase.GetRandomMeal
import dev.subfly.kmmeal.domain.useCase.LikeMeal
import dev.subfly.kmmeal.domain.useCase.RemoveLikedMeal
import dev.subfly.kmmeal.domain.useCase.SearchMeal
import org.koin.core.module.Module
import org.koin.dsl.module


internal val useCaseModule: Module = module {
    // All "gets" associated with Meal Repository
    factory { FetchCategories(get()) }
    factory { GetMealDetail(get()) }
    factory { GetRandomMeal(get()) }
    factory { SearchMeal(get()) }
    factory { SearchMeal(get()) }

    // All "gets" associated with Realm
    factory { GetAllLikedMeals(get()) }
    factory { GetLikedMeal(get()) }
    factory { LikeMeal(get()) }
    factory { RemoveLikedMeal(get()) }
    factory { DeleteAllLikedMeals(get()) }
}