package com.swyt.aplazotest.di

import android.app.Application
import com.swyt.aplazotest.viewModel.CategoriesViewModel
import com.swyt.aplazotest.viewModel.DetailMealViewModel
import com.swyt.aplazotest.viewModel.MealsViewModel
import com.swyt.aplazotest.viewModel.ViewModelFactory
import com.swyt.provider.di.providerServicesModules
import com.swyt.provider.di.providerUseCaseModel
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
                    providerServicesModules,
                    providerUseCaseModel
                )
            )
            koin.createRootScope()
        }
    }

    private val appModules = module {
        viewModel { CategoriesViewModel(get(), get(), get(), get()) }
        viewModel { MealsViewModel(get(), get()) }
        viewModel { DetailMealViewModel(get(), get()) }

        factory { ViewModelFactory(get(), get(), get(), get(), get(), get()) }
    }

}