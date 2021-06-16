package io.marleyspoonscodingchallenge.data.homepage.repository

import io.marleyspoonscodingchallenge.data.datasource.RemoteDataSource
import io.marleyspoonscodingchallenge.data.datasource.resultFlow
import io.marleyspoonscodingchallenge.data.homepage.api.GetAllRecipesService
import io.marleyspoonscodingchallenge.data.homepage.model.RecipesData
import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.homepage.model.RecipesModel
import io.marleyspoonscodingchallenge.domain.homepage.repository.GetAllRecipesRepository
import kotlinx.coroutines.flow.Flow

internal class GetAllRecipesRepositoryImpl(
  private val getAllRecipesService: GetAllRecipesService,
  private val mapper: Mapper<RecipesData, RecipesModel>
) : GetAllRecipesRepository, RemoteDataSource() {

  override fun getAllRecipes(): Flow<DataSourceResultHolder<RecipesModel>> {
    return resultFlow(
      networkCall = {
        getResult(
          call = { getAllRecipesService.getAllRecipes() },
          transform = { mapper.map(it) }
        )
      }
    )
  }
}