package dev.subfly.kmmeal.di.modules

import dev.subfly.kmmeal.data.local.entities.IngredientEntity
import dev.subfly.kmmeal.data.local.entities.LikedMealEntity
import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.module.Module
import org.koin.dsl.module

internal val databaseModule: Module = module {
    single<Configuration> {
        // Liked Meals Configuration
        RealmConfiguration.create(
            schema = setOf(
                LikedMealEntity::class,
                IngredientEntity::class
            )
        )
    }
    single {
        // Get configuration from above
        Realm.open(get())
    }
}