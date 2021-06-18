package io.marleyspoonscodingchallenge.domain.repository

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.model.RecipesModel
import kotlinx.coroutines.flow.Flow

interface GetAllRecipesRepository {
  fun getAllRecipes(): Flow<DataSourceResultHolder<RecipesModel>>
}