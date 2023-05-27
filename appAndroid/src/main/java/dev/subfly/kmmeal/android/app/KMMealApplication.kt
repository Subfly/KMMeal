package dev.subfly.kmmeal.android.app

import android.app.Application
import dev.subfly.kmmeal.android.di.appModule
import dev.subfly.kmmeal.di.DIHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class KMMealApplication: Application() {
    private val koin = DIHelper()

    override fun onCreate() {
        super.onCreate()
        koin.initKoin {
            androidLogger()
            androidContext(this@KMMealApplication)
            modules(appModule)
        }
    }
}