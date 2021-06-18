package io.marleyspoonscodingchallenge.domain

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.factory.DataFactory
import io.marleyspoonscodingchallenge.domain.repository.GetAllRecipesRepository
import io.marleyspoonscodingchallenge.domain.repository.GetRecipeRepository
import io.marleyspoonscodingchallenge.domain.usecase.GetAllRecipesUseCase
import io.marleyspoonscodingchallenge.domain.usecase.GetRecipeUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class GetAllRecipesUseCaseTest {

  private val repository: GetAllRecipesRepository = mockk()

  @Test
  fun testSearchMusicSessionsUseCase() = runBlocking {
    val item = DataSourceResultHolder.success(DataFactory.recipesModel)
    coEvery { repository.getAllRecipes() } returns flowOf(item)

    val useCase = GetAllRecipesUseCase(repository = repository)
    val result = useCase().first()

    assertEquals(item, result)
  }
}