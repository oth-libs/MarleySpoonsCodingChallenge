package io.marleyspoonscodingchallenge.data.remote.api

import io.marleyspoonscodingchallenge.data.remote.model.RecipesRetrofitData
import retrofit2.Response
import retrofit2.http.GET

internal interface GetAllRecipesService {

  @GET("spaces/kk2bw5ojx476/entries?access_token=7ac531648a1b5e1dab6c18b0979f822a5aad0fe5f1109829b8a197eb2be4b84c&content_type=recipe")
  suspend fun getAllRecipes(): Response<RecipesRetrofitData>

}
