package com.swyt.provider.di

import com.swyt.provider.API
import com.swyt.provider.services.GetListCategoriesServices
import com.swyt.provider.usecases.UserGetListCategoriesUseCase
import com.swyt.provider.usecases.UserGetListCategoriesUseCaseImpl
import org.koin.dsl.module

val providerServicesModules = module {
    factory { API.getServices<GetListCategoriesServices>() }
}

val providerUseCaseModel = module {
    single<UserGetListCategoriesUseCase> { UserGetListCategoriesUseCaseImpl(get()) }
}