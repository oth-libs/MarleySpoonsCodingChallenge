package io.marleyspoonscodingchallenge.domain.homepage.model

data class RecipesModel(
  val recipes: List<RecipeItem>?
)

data class RecipeItem(
  val title: String?,
  val photoUrl: String?
)

























