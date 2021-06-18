package io.marleyspoonscodingchallenge.ui.homepage

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import io.marleyspoonscodingchallenge.BaseFragment
import io.marleyspoonscodingchallenge.R
import io.marleyspoonscodingchallenge.databinding.FragmentHomepageBinding
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.extensions.navigate
import io.marleyspoonscodingchallenge.extensions.setup
import io.marleyspoonscodingchallenge.presentation.HomePageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class HomePageFragment : BaseFragment<FragmentHomepageBinding, HomePageViewModel>(
  layoutId = R.layout.fragment_homepage,
) {

  private val recipesAdapter by lazy { RecipesAdapter(viewModel.recipesData, ::onRecipeSelected) }

  override fun setupBinding() {
    binding.viewModel = viewModel

    setupViews()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    observeViewModelCalls()
  }

  private fun setupViews() {
    binding.rvRecipes.apply {
      setup()
      adapter = recipesAdapter
    }
  }

  private fun observeViewModelCalls() {
    viewModel.recipesDataResponse.observe(viewLifecycleOwner, ::recipesDataResponseReceived)
  }

  private fun recipesDataResponseReceived(@Suppress("UNUSED_PARAMETER") unit: Unit) {
    recipesAdapter.notifyDataSetChanged()
  }

  private fun onRecipeSelected(recipeItem: RecipeItem) {
    HomePageFragmentDirections.actionToRecipeDetail(recipeId = recipeItem.id).navigate(findNavController())
  }
}