package io.marleyspoonscodingchallenge.data.room.mapper

import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.data.room.factory.DataFactory
import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem
import io.marleyspoonscodingchallenge.data.room.model.RecipesRoomData
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.model.RecipesModel
import org.junit.Test
import kotlin.test.assertEquals

class MapperRecipesModelToRoomDataTest {

  private val mapper: Mapper<List<RecipeRoomItem>, RecipesModel> = MapperRecipesRoomDataToModel()

  @Test
  fun testMapper() {
    val expected = DataFactory.recipesModel

    val result = mapper.map(DataFactory.recipesRoomData.recipes!!)

    assertEquals(expected, result)
  }

}