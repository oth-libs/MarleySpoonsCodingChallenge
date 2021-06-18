package io.marleyspoonscodingchallenge.presentation

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.usecase.GetAllRecipesUseCase
import io.marleyspoonscodingchallenge.presentation.livedata.SingleLiveEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomePageViewModel(
  private val getAllRecipesUseCase: GetAllRecipesUseCase,
  application: Application
) : BaseViewModel(application) {

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // __      ___                 _      _           _____        _
  // \ \    / (_)               | |    (_)         |  __ \      | |
  //  \ \  / / _  _____      __ | |     ___   _____| |  | | __ _| |_ __ _
  //   \ \/ / | |/ _ \ \ /\ / / | |    | \ \ / / _ \ |  | |/ _` | __/ _` |
  //    \  /  | |  __/\ V  V /  | |____| |\ V /  __/ |__| | (_| | || (_| |
  //     \/   |_|\___| \_/\_/   |______|_| \_/ \___|_____/ \__,_|\__\__,_|
  //
  //Font Name: Big
  private val _recipesDataResponse = SingleLiveEvent<Unit>()
  val recipesDataResponse: LiveData<Unit> = _recipesDataResponse

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  _____        _
  // |  __ \      | |
  // | |  | | __ _| |_ __ _
  // | |  | |/ _` | __/ _` |
  // | |__| | (_| | || (_| |
  // |_____/ \__,_|\__\__,_|
  //
  //Font Name: Big
  private val _recipesData = mutableListOf<RecipeItem>()
  val recipesData: List<RecipeItem> = _recipesData

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  _    _           _____
  // | |  | |         / ____|
  // | |  | |___  ___| |     __ _ ___  ___
  // | |  | / __|/ _ \ |    / _` / __|/ _ \
  // | |__| \__ \  __/ |___| (_| \__ \  __/
  //  \____/|___/\___|\_____\__,_|___/\___|
  //
  //Font Name: Big
  /**
   * Start app with loading the recipes
   */
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  @Suppress("UNUSED") private fun loadRecipes() {
    viewModelScope.launch {
      getAllRecipesUseCase().collect { response ->
        println("aaaaaaaaaaa $response")

        when (response.status) {
          DataSourceResultHolder.Status.ERROR -> {
            genericErrorLiveData.value = Unit
          }

          DataSourceResultHolder.Status.NO_INTERNET -> {
            internetErrorLiveData.value = Unit
          }

          DataSourceResultHolder.Status.SUCCESS -> {
            response.data?.let { recipesModel ->
              _recipesData.apply { clear(); addAll(recipesModel.recipes ?: emptyList()) }
              _recipesDataResponse.value = Unit
            }
          }

          else -> Unit
        }
      }
    }
  }

}