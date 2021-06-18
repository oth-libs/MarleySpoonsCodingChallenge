package io.marleyspoonscodingchallenge.presentation.di

import io.marleyspoonscodingchallenge.presentation.HomePageViewModel
import io.marleyspoonscodingchallenge.presentation.RecipeDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {

  fun load() {
    loadKoinModules(module {
      viewModel { HomePageViewModel(getAllRecipesUseCase = get(), application = get()) }
      viewModel { (recipeId: String) -> RecipeDetailViewModel(recipeId = recipeId, getRecipeUseCase = get(), application = get()) }
    })
  }
}