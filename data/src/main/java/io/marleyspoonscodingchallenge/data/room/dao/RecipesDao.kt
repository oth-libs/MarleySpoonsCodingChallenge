package io.marleyspoonscodingchallenge.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.marleyspoonscodingchallenge.data.room.TABLE_RECIPES
import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RecipesDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(recipes: List<RecipeRoomItem>)

  @Query("SELECT * FROM $TABLE_RECIPES")
  fun selectAll(): Flow<List<RecipeRoomItem>>

  @Query("SELECT * FROM $TABLE_RECIPES WHERE id = :recipeId")
  fun selectWithId(recipeId: String): Flow<RecipeRoomItem>
}