package dev.subfly.kmmeal.di.modules

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
internal val networkModule: Module = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
            prettyPrint = true
            useAlternativeNames = false
            explicitNulls = false
        }
    }
    single {
        // Get engine from platform
        HttpClient(get()) {
            install(HttpTimeout) {
                requestTimeoutMillis = 5000L
                connectTimeoutMillis = 5000L
            }
            install(ContentNegotiation) {
                // Get Json from previous single
                json(get())
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}