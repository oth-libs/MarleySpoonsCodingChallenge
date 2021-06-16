package com.doublesymetrymusic.presentation.di

import io.marleyspoonscodingchallenge.presentation.homepage.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {

  fun load() {
    loadKoinModules(module {
      viewModel { HomePageViewModel(getAllRecipesUseCase = get(), application = get()) }
    })
  }
}