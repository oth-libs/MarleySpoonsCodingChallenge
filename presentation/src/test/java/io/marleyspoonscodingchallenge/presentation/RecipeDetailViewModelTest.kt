package io.marleyspoonscodingchallenge.presentation

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.usecase.GetRecipeUseCase
import io.marleyspoonscodingchallenge.presentation.factory.DataFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class RecipeDetailViewModelTest {

  @get:Rule
  val rule = InstantTaskExecutorRule()

  @get:Rule
  val testCoroutineRule = TestCoroutineRule()

  private val getRecipeUseCase = mockk<GetRecipeUseCase>()
  private val application = mockk<Application>(relaxed = true)

  private val viewModel = RecipeDetailViewModel(DataFactory.recipeItem.id, getRecipeUseCase, application)

  @Test
  fun testLoadRecipesSuccess() {
    coEvery { getRecipeUseCase(DataFactory.recipeItem.id) } returns flowOf(DataSourceResultHolder.success(DataFactory.recipeItem))

    viewModel.loadRecipe()
    coVerify(exactly = 1) { getRecipeUseCase(DataFactory.recipeItem.id) }
    assertEquals(viewModel.recipeItem.getOrAwaitValue(), DataFactory.recipeItem)
  }
}