package io.marleyspoonscodingchallenge.data.room.factory

import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem
import io.marleyspoonscodingchallenge.data.room.model.RecipesRoomData
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.model.RecipesModel

internal object DataFactory {

  val recipeRoomItem = RecipeRoomItem(
    id = "id123",
    title = "title123",
    photoUrl = "url123",
    tags = listOf("tag1"),
    description = "description123",
    chefName = "chef123"
  )
  val recipesRoomData = RecipesRoomData(
    recipes = listOf(
      recipeRoomItem
    )
  )

  val recipeItem = RecipeItem(
    id = "id123",
    title = "title123",
    photoUrl = "url123",
    tags = listOf("tag1"),
    description = "description123",
    chefName = "chef123"
  )
  val recipesModel = RecipesModel(
    recipes = listOf(
      recipeItem
    )
  )
}