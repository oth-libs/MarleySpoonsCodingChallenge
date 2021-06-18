package io.marleyspoonscodingchallenge.ui.recipedetail

import io.marleyspoonscodingchallenge.BaseFragment
import io.marleyspoonscodingchallenge.R
import io.marleyspoonscodingchallenge.databinding.FragmentRecipeDetailBinding
import io.marleyspoonscodingchallenge.presentation.RecipeDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class RecipeDetailFragment : BaseFragment<FragmentRecipeDetailBinding, RecipeDetailViewModel>(
  layoutId = R.layout.fragment_recipe_detail,
  parameterName = "recipeId"
) {

  override fun setupBinding() {
    binding.viewModel = viewModel
  }
}