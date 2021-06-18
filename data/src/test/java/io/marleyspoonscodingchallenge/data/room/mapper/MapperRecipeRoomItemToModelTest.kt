package io.marleyspoonscodingchallenge.data.room.mapper

import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.data.room.factory.DataFactory
import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import org.junit.Test
import kotlin.test.assertEquals

class MapperRecipeRoomItemToModelTest {

  private val mapper: Mapper<RecipeRoomItem, RecipeItem> = MapperRecipeRoomItemToModel()

  @Test
  fun testMapper() {
    val expected = DataFactory.recipeItem

    val result = mapper.map(DataFactory.recipeRoomItem)

    assertEquals(expected, result)
  }

}