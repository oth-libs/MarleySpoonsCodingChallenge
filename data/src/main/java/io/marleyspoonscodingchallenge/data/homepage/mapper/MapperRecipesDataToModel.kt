package io.marleyspoonscodingchallenge.data.homepage.mapper

import io.marleyspoonscodingchallenge.data.homepage.model.Includes
import io.marleyspoonscodingchallenge.data.homepage.model.RecipesData
import io.marleyspoonscodingchallenge.data.homepage.model.SysContentType
import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.domain.homepage.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.homepage.model.RecipesModel

/**
 * Map [RecipesData] from retrofit to the model [RecipesModel]
 */
internal class MapperRecipesDataToModel : Mapper<RecipesData, RecipesModel> {

  override fun map(from: RecipesData): RecipesModel {
    return from.run {
      RecipesModel(
        recipes = mapRecipes(from),
      )
    }
  }

  private fun mapRecipes(recipesData: RecipesData?): List<RecipeItem> {
    return mutableListOf<RecipeItem>().apply {
      recipesData?.run {
        items?.filter { it.sys?.contentType?.sys == recipeContentType }?.forEach {
          val recipeItem = RecipeItem(
            title = it.fields?.title,
            photoUrl = mapPhotoUrl(recipesData.includes, it.fields?.photo?.sys?.id)
          )

          add(recipeItem)
        }
      }
    }
  }

  private fun mapPhotoUrl(includes: Includes?, photoId: String?): String? {
    return includes?.Asset?.firstOrNull { it.sys?.id == photoId }?.fields?.file?.url?.replaceFirst("//", "https://")
  }

  companion object {
    private val recipeContentType = SysContentType(type = "Link", linkType = "ContentType", id = "recipe")
  }
}