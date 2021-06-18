package io.marleyspoonscodingchallenge.domain.usecase

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.repository.GetRecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipeUseCase(private val repository: GetRecipeRepository) {

  operator fun invoke(recipeId: String): Flow<DataSourceResultHolder<RecipeItem>> = repository.getRecipe(recipeId)

}