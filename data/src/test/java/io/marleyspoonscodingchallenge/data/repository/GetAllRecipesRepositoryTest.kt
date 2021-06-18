package io.marleyspoonscodingchallenge.data.repository

import io.marleyspoonscodingchallenge.data.remote.api.GetAllRecipesService
import io.marleyspoonscodingchallenge.data.remote.mapper.MapperRecipesRetrofitDataToModel
import io.marleyspoonscodingchallenge.data.room.dao.RecipesDao
import io.marleyspoonscodingchallenge.data.room.factory.DataFactory
import io.marleyspoonscodingchallenge.data.room.mapper.MapperRecipesModelToRoomData
import io.marleyspoonscodingchallenge.data.room.mapper.MapperRecipesRoomDataToModel
import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.repository.GetAllRecipesRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

internal class GetAllRecipesRepositoryTest {

  private val getAllRecipesService: GetAllRecipesService = mockk(relaxed = true)
  private val recipesDao: RecipesDao = mockk(relaxed = true)
  private val repository: GetAllRecipesRepository = GetAllRecipesRepositoryImp(
    getAllRecipesService = getAllRecipesService,
    mapperRecipesRetrofitDataToModel = MapperRecipesRetrofitDataToModel(),

    recipesDao = recipesDao,
    mapperRecipesRoomDataToModel = MapperRecipesRoomDataToModel(),
    mapperRecipesModelToRoomData = MapperRecipesModelToRoomData()
  )

  @Test
  fun testGetAllRecipes() = runBlocking {
    val expected = DataFactory.recipesModel

    every { repository.getAllRecipes() } returns flowOf(DataSourceResultHolder.success(DataFactory.recipesModel))
    every { recipesDao.selectAll() } returns flowOf(DataFactory.recipesRoomData.recipes!!)
    coEvery { getAllRecipesService.getAllRecipes() } returns Response.success(io.marleyspoonscodingchallenge.data.remote.factory.DataFactory.recipesRetrofitData)

    val result = repository.getAllRecipes().first().data

    assertEquals(expected, result)
  }
}