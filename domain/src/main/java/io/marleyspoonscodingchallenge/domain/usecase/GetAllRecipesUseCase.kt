package io.marleyspoonscodingchallenge.domain.usecase

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.model.RecipesModel
import io.marleyspoonscodingchallenge.domain.repository.GetAllRecipesRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecipesUseCase(private val repository: GetAllRecipesRepository) {

  operator fun invoke(): Flow<DataSourceResultHolder<RecipesModel>> = repository.getAllRecipes()

}