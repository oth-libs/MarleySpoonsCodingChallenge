package io.marleyspoonscodingchallenge.data.repository

import io.marleyspoonscodingchallenge.data.remote.api.GetAllRecipesService
import io.marleyspoonscodingchallenge.data.room.dao.RecipesDao
import io.marleyspoonscodingchallenge.data.room.factory.DataFactory
import io.marleyspoonscodingchallenge.data.room.mapper.MapperRecipeRoomItemToModel
import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.repository.GetRecipeRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class GetRecipeRepositoryTest {

  private lateinit var recipesDao: RecipesDao
  private lateinit var repository: GetRecipeRepository

  @Before
  fun setUp() {
    recipesDao = mockk(relaxed = true)

    repository = GetRecipeRepositoryImp(
      recipesDao = recipesDao,
      mapperRecipeRoomItemToModel = MapperRecipeRoomItemToModel()
    )
  }

  @Test
  fun testGetRecipe() = runBlocking {
    val expected = DataFactory.recipeItem

    every { repository.getRecipe(DataFactory.recipeItem.id) } returns flowOf(DataSourceResultHolder.success(DataFactory.recipeItem))
    every { recipesDao.selectWithId(DataFactory.recipeItem.id) } returns flowOf(DataFactory.recipeRoomItem)

    val result = repository.getRecipe(DataFactory.recipeItem.id).first().data

    assertEquals(expected, result)
  }
}