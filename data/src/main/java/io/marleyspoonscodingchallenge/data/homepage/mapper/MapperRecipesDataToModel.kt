package io.marleyspoonscodingchallenge.data.homepage.mapper

import io.marleyspoonscodingchallenge.data.homepage.model.Asset
import io.marleyspoonscodingchallenge.data.homepage.model.Chef
import io.marleyspoonscodingchallenge.data.homepage.model.Entry
import io.marleyspoonscodingchallenge.data.homepage.model.Photo
import io.marleyspoonscodingchallenge.data.homepage.model.RecipesData
import io.marleyspoonscodingchallenge.data.homepage.model.Tag
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
        items?.forEach { item ->
          if (item.sys?.id == null) return@forEach

          val recipeItem = RecipeItem(
            id = item.sys.id,
            title = item.fields?.title,
            photoUrl = mapPhotoUrl(recipesData.includes?.Asset, item.fields?.photo),
            tags = mapTags(recipesData.includes?.Entry, item.fields?.tags),
            description = item.fields?.description,
            chefName = mapChefName(recipesData.includes?.Entry, item.fields?.chef)
          )

          add(recipeItem)
        }
      }
    }
  }

  private fun mapPhotoUrl(assets: List<Asset>?, photo: Photo?): String? {
    return assets?.firstOrNull { it.sys?.id == photo?.sys?.id }?.fields?.file?.url?.replaceFirst("//", "https://")
  }

  private fun mapTags(entries: List<Entry>?, tags: List<Tag>?): List<String>? {
    return tags?.let {
      mutableListOf<String>().apply {
        tags.forEach { tag ->
          entries?.firstOrNull { it.sys?.id == tag.sys?.id }?.fields?.name?.let { tagName -> add(tagName) }
        }
      }
    }
  }

  private fun mapChefName(entries: List<Entry>?, chef: Chef?): String? {
    return chef?.let { entries?.firstOrNull { it.sys?.id == chef.sys?.id } }?.fields?.name
  }

}