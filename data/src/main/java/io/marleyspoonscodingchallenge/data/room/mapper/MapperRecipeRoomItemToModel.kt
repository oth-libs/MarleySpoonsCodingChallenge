package io.marleyspoonscodingchallenge.data.room.mapper

import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem
import io.marleyspoonscodingchallenge.data.room.model.RecipesRoomData
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.model.RecipesModel

/**
 * Map [RecipesRoomData] from room to the model [RecipesModel]
 */
internal class MapperRecipeRoomItemToModel : Mapper<RecipeRoomItem, RecipeItem> {
  override fun map(from: RecipeRoomItem): RecipeItem {
    return from.run {
      mapRecipeItem(this)
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