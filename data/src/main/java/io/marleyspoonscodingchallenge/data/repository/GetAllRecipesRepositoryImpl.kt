package io.marleyspoonscodingchallenge.data.repository

import io.marleyspoonscodingchallenge.data.remote.datasource.RemoteDataSource.getResult
import io.marleyspoonscodingchallenge.data.room.datasource.RoomDataSource.getRoomResult
import io.marleyspoonscodingchallenge.data.datasource.resultFlow
import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.data.remote.api.GetAllRecipesService
import io.marleyspoonscodingchallenge.data.remote.model.RecipesRetrofitData
import io.marleyspoonscodingchallenge.data.room.dao.RecipesDao
import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem
import io.marleyspoonscodingchallenge.data.room.model.RecipesRoomData
import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.model.RecipesModel
import io.marleyspoonscodingchallenge.domain.repository.GetAllRecipesRepository
import kotlinx.coroutines.flow.Flow

internal class GetAllRecipesRepositoryImpl(
  private val getAllRecipesService: GetAllRecipesService,
  private val mapperRecipesRetrofitDataToModel: Mapper<RecipesRetrofitData, RecipesModel>,

  private val recipesDao: RecipesDao,
  private val mapperRecipesRoomDataToModel: Mapper<List<RecipeRoomItem>, RecipesModel>,
  private val mapperRecipesModelToRoomData: Mapper<RecipesModel, RecipesRoomData>
) : GetAllRecipesRepository {

  override fun getAllRecipes(): Flow<DataSourceResultHolder<RecipesModel>> {
    return resultFlow(
      selectQuery = {
        getRoomResult(
          call = { recipesDao.selectAll() },
          transform = { mapperRecipesRoomDataToModel.map(it) }
        )
      },

      networkCall = {
        getResult(
          call = { getAllRecipesService.getAllRecipes() },
          transform = { mapperRecipesRetrofitDataToModel.map(it) }
        )
      },

      insertResultQuery = {
        // map model to room model and insert the list
        mapperRecipesModelToRoomData.map(it).recipes?.let { recipes -> recipesDao.insert(recipes) }
      }
    )
  }
}