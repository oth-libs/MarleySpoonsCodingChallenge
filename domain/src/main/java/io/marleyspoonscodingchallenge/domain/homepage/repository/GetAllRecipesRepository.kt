package io.marleyspoonscodingchallenge.domain.homepage.repository

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.homepage.model.RecipesModel
import kotlinx.coroutines.flow.Flow

interface GetAllRecipesRepository {
  fun getAllRecipes(): Flow<DataSourceResultHolder<RecipesModel>>
}