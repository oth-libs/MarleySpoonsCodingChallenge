package io.marleyspoonscodingchallenge.domain.di

import io.marleyspoonscodingchallenge.domain.homepage.usecase.GetAllRecipesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {

  fun load() {
    loadKoinModules(module {
      single { GetAllRecipesUseCase(repository = get()) }
    })
  }
}