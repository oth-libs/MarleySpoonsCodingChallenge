package io.marleyspoonscodingchallenge.data.room.mapper

import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.data.room.factory.DataFactory
import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem
import io.marleyspoonscodingchallenge.data.room.model.RecipesRoomData
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.model.RecipesModel
import org.junit.Test
import kotlin.test.assertEquals

class MapperRecipesRoomDataToModelTest {

  private val mapper: Mapper<RecipesModel, RecipesRoomData> = MapperRecipesModelToRoomData()

  @Test
  fun testMapper() {
    val expected = DataFactory.recipesRoomData

    val result = mapper.map(DataFactory.recipesModel)

    assertEquals(expected, result)
  }

}