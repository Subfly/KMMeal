package dev.subfly.kmmeal.di.modules

import io.ktor.client.engine.android.Android
import org.koin.dsl.module

actual fun provideEngineAsModule() = module {
    single { Android.create() }
}