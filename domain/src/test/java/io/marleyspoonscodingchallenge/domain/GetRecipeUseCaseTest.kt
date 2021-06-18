package io.marleyspoonscodingchallenge.domain

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.factory.DataFactory
import io.marleyspoonscodingchallenge.domain.repository.GetRecipeRepository
import io.marleyspoonscodingchallenge.domain.usecase.GetRecipeUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class GetRecipeUseCaseTest {

  private val repository: GetRecipeRepository = mockk()

  @Test
  fun testGetRecipeUseCase() = runBlocking {
    val item = DataSourceResultHolder.success(DataFactory.recipeItem)
    coEvery { repository.getRecipe("123") } returns flowOf(item)

    val useCase = GetRecipeUseCase(repository = repository)
    val result = useCase("123").first()

    assertEquals(item, result)
  }
}