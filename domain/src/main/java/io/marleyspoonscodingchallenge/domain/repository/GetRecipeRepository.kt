package io.marleyspoonscodingchallenge.domain.repository

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import kotlinx.coroutines.flow.Flow

interface GetRecipeRepository {
  fun getRecipe(recipeId: String): Flow<DataSourceResultHolder<RecipeItem>>
}