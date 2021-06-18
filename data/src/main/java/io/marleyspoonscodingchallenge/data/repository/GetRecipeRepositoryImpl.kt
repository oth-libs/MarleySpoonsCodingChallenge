package io.marleyspoonscodingchallenge.data.repository

import io.marleyspoonscodingchallenge.data.datasource.RoomDataSource
import io.marleyspoonscodingchallenge.data.datasource.resultFlow
import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.data.room.dao.RecipesDao
import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem
import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.repository.GetRecipeRepository
import kotlinx.coroutines.flow.Flow

internal class GetRecipeRepositoryImpl(
  private val roomDataSource: RoomDataSource,

  private val recipesDao: RecipesDao,
  private val mapperRecipeRoomItemToModel: Mapper<RecipeRoomItem, RecipeItem>,
) : GetRecipeRepository {

  override fun getRecipe(recipeId: String): Flow<DataSourceResultHolder<RecipeItem>> {
    return resultFlow(
      selectQuery = {
        roomDataSource.getRoomResult2(
          call = { recipesDao.selectWithId(recipeId) },
          transform = { mapperRecipeRoomItemToModel.map(it) }
        )
      }
    )
  }
}