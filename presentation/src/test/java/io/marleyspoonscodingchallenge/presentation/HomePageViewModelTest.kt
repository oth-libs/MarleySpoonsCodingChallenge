package io.marleyspoonscodingchallenge.presentation

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.usecase.GetAllRecipesUseCase
import io.marleyspoonscodingchallenge.presentation.factory.DataFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class HomePageViewModelTest {

  @get:Rule
  val rule = InstantTaskExecutorRule()

  @get:Rule
  val testCoroutineRule = TestCoroutineRule()

  private val getAllRecipesUseCase = mockk<GetAllRecipesUseCase>()
  private val application = mockk<Application>(relaxed = true)

  private val viewModel = HomePageViewModel(getAllRecipesUseCase, application)

  @Test
  fun testLoadRecipesSuccess() {
    coEvery { getAllRecipesUseCase() } returns flowOf(DataSourceResultHolder.success(DataFactory.recipesModel))

    viewModel.loadRecipes()
    coVerify(exactly = 1) { getAllRecipesUseCase() }
    assertEquals(viewModel.recipesData.size, 1)
    assertEquals(viewModel.recipesDataResponse.getOrAwaitValue(), Unit)
  }

  @Test
  fun testLoadRecipesError() {
    coEvery { getAllRecipesUseCase() } returns flowOf(DataSourceResultHolder.error())

    viewModel.loadRecipes()
    coVerify(exactly = 1) { getAllRecipesUseCase() }
    assertEquals(viewModel.recipesData.size, 0)
    assertEquals(viewModel.genericErrorLiveData.getOrAwaitValue(), Unit)
  }

 @Test
  fun testLoadRecipesInternetError() {
    coEvery { getAllRecipesUseCase() } returns flowOf(DataSourceResultHolder.noInternet())

    viewModel.loadRecipes()
    coVerify(exactly = 1) { getAllRecipesUseCase() }
    assertEquals(viewModel.recipesData.size, 0)
    assertEquals(viewModel.internetErrorLiveData.getOrAwaitValue(), Unit)
  }

}