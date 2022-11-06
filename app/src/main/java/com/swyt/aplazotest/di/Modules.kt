package com.swyt.aplazotest.di

import android.app.Application
import com.swyt.aplazotest.viewModel.MainViewModel
import com.swyt.aplazotest.viewModel.ViewModelFactory
import com.swyt.provider.di.providerModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object Modules {

    fun init(application: Application) {
        startKoin {
            androidLogger()
            androidContext(application)
            androidFileProperties()
            koin.loadModules(
                arrayListOf(
                    appModules,
                    providerModules
                )
            )
            koin.createRootScope()
        }
    }

    private val appModules = module {
        viewModel { MainViewModel(get()) }
        factory { ViewModelFactory(get()) }
    }

}