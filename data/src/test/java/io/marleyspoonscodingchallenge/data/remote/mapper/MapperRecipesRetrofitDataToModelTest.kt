package io.marleyspoonscodingchallenge.data.remote.mapper

import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.data.remote.factory.DataFactory
import io.marleyspoonscodingchallenge.data.remote.model.RecipesRetrofitData
import io.marleyspoonscodingchallenge.domain.model.RecipesModel
import org.junit.Test
import kotlin.test.assertEquals

class MapperRecipesRetrofitDataToModelTest {

  private val mapper: Mapper<RecipesRetrofitData, RecipesModel> = MapperRecipesRetrofitDataToModel()

  @Test
  fun testMapper() {
    val expected = DataFactory.recipesModel

    val result = mapper.map(DataFactory.recipesRetrofitData)

    assertEquals(expected, result)
  }

}