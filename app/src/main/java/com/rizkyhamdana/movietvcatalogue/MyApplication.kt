package com.rizkyhamdana.movietvcatalogue

import android.app.Application
import com.rizkyhamdana.movietvcatalogue.core.di.databaseModule
import com.rizkyhamdana.movietvcatalogue.core.di.networkModule
import com.rizkyhamdana.movietvcatalogue.core.di.repositoryModule
import com.rizkyhamdana.movietvcatalogue.di.useCaseModule
import com.rizkyhamdana.movietvcatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}