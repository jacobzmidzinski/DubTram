package com.jacobzmidzinski.dubtram

import android.app.Application
import com.jacobzmidzinski.dubtram.data.di.dataModule
import com.jacobzmidzinski.dubtram.domain.di.domainModule
import com.jacobzmidzinski.dubtram.presentation.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DubTramApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DubTramApplication)

            modules(
                appModule,
                dataModule,
                domainModule
            )
        }
    }
}