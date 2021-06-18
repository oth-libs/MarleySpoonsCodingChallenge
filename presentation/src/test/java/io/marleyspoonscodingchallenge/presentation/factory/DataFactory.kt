package io.marleyspoonscodingchallenge.presentation.factory

import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.model.RecipesModel

internal object DataFactory {

  val recipeItem = RecipeItem(
    id = "id123",
    title = "title123",
    photoUrl = "url123",
    tags = listOf("tag1", "tag2"),
    description = "description123",
    chefName = "chef123"
  )

  val recipesModel = RecipesModel(
    recipes = listOf(recipeItem)
  )
}