package dev.subfly.kmmeal.di

import dev.subfly.kmmeal.di.modules.databaseModule
import dev.subfly.kmmeal.di.modules.networkModule
import dev.subfly.kmmeal.di.modules.provideEngineAsModule
import dev.subfly.kmmeal.di.modules.repositoryModule
import dev.subfly.kmmeal.di.modules.useCaseModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class DIHelper {
    fun initKoin(
        appDeclaration: KoinAppDeclaration = {}
    ) = startKoin {
        appDeclaration()
        modules(
            provideEngineAsModule(),
            databaseModule,
            networkModule,
            repositoryModule,
            useCaseModule
        )
    }

    // For iOS
    fun initKoin() = initKoin {}
}