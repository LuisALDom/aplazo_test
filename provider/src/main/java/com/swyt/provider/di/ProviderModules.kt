package com.swyt.provider.di

import com.swyt.provider.API
import com.swyt.provider.services.*
import com.swyt.provider.usecases.*
import com.swyt.provider.usecases.UserGetListCategoriesUseCaseImpl
import com.swyt.provider.usecases.UserGetListMealsUseCaseImpl
import org.koin.dsl.module

val providerServicesModules = module {
    factory { API.getServices<GetListCategoriesServices>() }
    factory { API.getServices<GetListMealsServices>() }
    factory { API.getServices<GetDetailMealServices>() }
    factory { API.getServices<GetSearchMealServices>() }
    factory { API.getServices<GetRandomMealServices>() }
}

val providerUseCaseModel = module {
    single<UserGetListCategoriesUseCase> { UserGetListCategoriesUseCaseImpl(get()) }
    single<UserGetListMealUseCase> { UserGetListMealsUseCaseImpl(get()) }
    single<UserGetDetailMealUseCase> { UserGetDetailMealUseCaseImpl(get()) }
    single<UserGetSearchMealUseCase> { UserGetSearchMealUseCaseImpl(get()) }
    single<UserGetRandomMealUseCase> { UserGetRandomMealUseCaseImpl(get()) }
}