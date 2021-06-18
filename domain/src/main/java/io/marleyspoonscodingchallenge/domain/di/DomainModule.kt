package io.marleyspoonscodingchallenge.domain.di

import io.marleyspoonscodingchallenge.domain.usecase.GetAllRecipesUseCase
import io.marleyspoonscodingchallenge.domain.usecase.GetRecipeUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {

  fun load() {
    loadKoinModules(module {
      single { GetAllRecipesUseCase(repository = get()) }
      single { GetRecipeUseCase(repository = get()) }
    })
  }
}