package dev.subfly.kmmeal.di.modules

import dev.subfly.kmmeal.data.local.database.LikedMealsDatabase
import dev.subfly.kmmeal.data.local.managers.LikedMealsManager
import dev.subfly.kmmeal.data.remote.repository.MealRepository
import org.koin.core.module.Module
import org.koin.dsl.module


internal val repositoryModule: Module = module {
    single {
        // Get HTTP Client from network module
        MealRepository(get())
    }
    single<LikedMealsDatabase> {
        // Get Realm from database module
        LikedMealsManager(get())
    }
}