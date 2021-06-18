package io.marleyspoonscodingchallenge.data.room.mapper

import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem
import io.marleyspoonscodingchallenge.data.room.model.RecipesRoomData
import io.marleyspoonscodingchallenge.domain.homepage.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.homepage.model.RecipesModel

/**
 * Map [RecipesModel] to the room model [RecipesRoomData]
 */
internal class MapperRecipesModelToRoomData : Mapper<RecipesModel, RecipesRoomData> {
  override fun map(from: RecipesModel): RecipesRoomData {
    return from.run {
      RecipesRoomData(
        recipes = mapRecipes(from.recipes),
      )
    }
  }

  private fun mapRecipes(recipesData: List<RecipeItem>?): List<RecipeRoomItem>? {
    return recipesData?.run {
      mutableListOf<RecipeRoomItem>().apply {
        recipesData.forEach { recipeItemData ->
          add(mapRecipeItem(recipeItemData))
        }
      }
    }
  }

  private fun mapRecipeItem(recipeItemData: RecipeItem): RecipeRoomItem {
    return recipeItemData.run {
      RecipeRoomItem(
        id = id,
        title = title,
        photoUrl = photoUrl,
        tags = tags?.toList(),
        description = description,
        chefName = chefName
      )
    }
  }

}