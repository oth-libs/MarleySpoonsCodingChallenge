package io.marleyspoonscodingchallenge.data.datasource

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

internal class RemoteDataSource {

  /**
   * Get single RETROFIT response
   */
  suspend fun <MODEL_API, MODEL> getResult(
    call: suspend () -> Response<MODEL_API>,
    transform: suspend (MODEL_API) -> MODEL,
  ): DataSourceResultHolder<MODEL> {
    try {

      // verify internet
      if (withContext(Dispatchers.IO) { !NetworkUtils.hasInternetConnection() }) {
        return DataSourceResultHolder.noInternet()
      }

      // call api
      val response = call()

      // map/transform
      val model = transform(response.body()!!)

      return when {
        response.isSuccessful && response.body() != null -> {
          DataSourceResultHolder.success(model)
        }

        else -> {
          DataSourceResultHolder.error()
        }
      }

    } catch (e: Exception) {
      e.printStackTrace()
      return DataSourceResultHolder.error()
    }
  }
}