package dev.subfly.kmmeal.di.modules

import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual fun provideEngineAsModule() = module {
    single { Darwin.create() }
}