package io.marleyspoonscodingchallenge.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.marleyspoonscodingchallenge.data.room.TABLE_RECIPES

internal data class RecipesRoomData(
  val recipes: List<RecipeRoomItem>?
)

@Entity(tableName = TABLE_RECIPES)
internal data class RecipeRoomItem(
  @PrimaryKey val id: String,
  val title: String?,
  val photoUrl: String?,
  val tags: List<String>?,
  val description: String?,
  val chefName: String?
)