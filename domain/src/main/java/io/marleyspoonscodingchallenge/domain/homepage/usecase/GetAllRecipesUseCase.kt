package io.marleyspoonscodingchallenge.domain.homepage.usecase

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.homepage.model.RecipesModel
import io.marleyspoonscodingchallenge.domain.homepage.repository.GetAllRecipesRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecipesUseCase(private val repository: GetAllRecipesRepository) {

  operator fun invoke(): Flow<DataSourceResultHolder<RecipesModel>> = repository.getAllRecipes()

}
