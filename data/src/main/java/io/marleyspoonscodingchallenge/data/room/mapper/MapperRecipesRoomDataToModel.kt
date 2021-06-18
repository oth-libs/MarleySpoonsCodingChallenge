package io.marleyspoonscodingchallenge.data.room.mapper

import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem
import io.marleyspoonscodingchallenge.data.room.model.RecipesRoomData
import io.marleyspoonscodingchallenge.domain.homepage.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.homepage.model.RecipesModel

/**
 * Map [RecipesRoomData] from room to the model [RecipesModel]
 */
internal class MapperRecipesRoomDataToModel : Mapper<List<RecipeRoomItem>, RecipesModel> {
  override fun map(from: List<RecipeRoomItem>): RecipesModel {
    return from.run {
      RecipesModel(
        recipes = mapRecipes(from),
      )
    }
  }

  private fun mapRecipes(recipesData: List<RecipeRoomItem>?): List<RecipeItem>? {
    return recipesData?.run {
      mutableListOf<RecipeItem>().apply {
        recipesData.forEach { recipeItemData ->
          add(mapRecipeItem(recipeItemData))
        }
      }
    }
  }

  private fun mapRecipeItem(recipeItemData: RecipeRoomItem): RecipeItem {
    return recipeItemData.run {
      RecipeItem(
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