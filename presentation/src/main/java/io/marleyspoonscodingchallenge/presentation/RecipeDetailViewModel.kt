package io.marleyspoonscodingchallenge.presentation

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.usecase.GetRecipeUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
  private val recipeId: String,
  private val getRecipeUseCase: GetRecipeUseCase,
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
  private val _recipeItem = MutableLiveData<RecipeItem>()
  val recipeItem: LiveData<RecipeItem> = _recipeItem

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
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  @Suppress("UNUSED") private fun loadRecipes() {
    println("aaaaaaaaaaa $recipeId")

    viewModelScope.launch {
      getRecipeUseCase(recipeId).collect { response ->
        println("aaaaaaaaaaa $response")

        _recipeItem.value = response.data
      }
    }
  }
}