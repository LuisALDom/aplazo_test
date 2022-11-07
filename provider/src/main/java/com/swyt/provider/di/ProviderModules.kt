package com.swyt.provider.di

import com.swyt.provider.API
import com.swyt.provider.services.GetListCategoriesServices
import com.swyt.provider.services.GetListMealsServices
import com.swyt.provider.usecases.UserGetListCategoriesUseCase
import com.swyt.provider.usecases.UserGetListCategoriesUseCaseImpl
import com.swyt.provider.usecases.UserGetListMealUseCase
import com.swyt.provider.usecases.UserGetListMealsUseCaseImpl
import org.koin.dsl.module

val providerServicesModules = module {
    factory { API.getServices<GetListCategoriesServices>() }
    factory { API.getServices<GetListMealsServices>() }
}

val providerUseCaseModel = module {
    single<UserGetListCategoriesUseCase> { UserGetListCategoriesUseCaseImpl(get()) }
    single<UserGetListMealUseCase> { UserGetListMealsUseCaseImpl(get()) }
}