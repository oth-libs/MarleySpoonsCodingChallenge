package io.marleyspoonscodingchallenge.domain.model

data class RecipesModel(
  val recipes: List<RecipeItem>?
)

data class RecipeItem(
  val id: String,
  val title: String?,
  val photoUrl: String?,
  val tags: List<String>?,
  val description: String?,
  val chefName: String?
) {
  fun getTagsString() = tags?.joinToString(", ")
}