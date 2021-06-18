package io.marleyspoonscodingchallenge.data.di

import io.marleyspoonscodingchallenge.data.datasource.RemoteDataSource
import io.marleyspoonscodingchallenge.data.datasource.RoomDataSource
import io.marleyspoonscodingchallenge.data.remote.RetrofitFactory
import io.marleyspoonscodingchallenge.data.remote.api.GetAllRecipesService
import io.marleyspoonscodingchallenge.data.remote.mapper.MapperRecipesRetrofitDataToModel
import io.marleyspoonscodingchallenge.data.repository.GetAllRecipesRepositoryImpl
import io.marleyspoonscodingchallenge.data.repository.GetRecipeRepositoryImpl
import io.marleyspoonscodingchallenge.data.room.RoomDB
import io.marleyspoonscodingchallenge.data.room.mapper.MapperRecipeRoomItemToModel
import io.marleyspoonscodingchallenge.data.room.mapper.MapperRecipesModelToRoomData
import io.marleyspoonscodingchallenge.data.room.mapper.MapperRecipesRoomDataToModel
import io.marleyspoonscodingchallenge.domain.repository.GetAllRecipesRepository
import io.marleyspoonscodingchallenge.domain.repository.GetRecipeRepository
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

object DataModule {

  @ExperimentalSerializationApi fun load() {
    loadKoinModules(module {
      //  retrofit
      single { RetrofitFactory().build() }
      single { (get() as Retrofit).create(GetAllRecipesService::class.java) }

      //Room
      single { RoomDB.getInstance(get()) }
      single { get<RoomDB>().recipesDao() }

      // data source
      single { RemoteDataSource() }
      single { RoomDataSource() }

      // repository
      single<GetAllRecipesRepository> {
        GetAllRecipesRepositoryImpl(
          remoteDataSource = get(),
          roomDataSource = get(),

          getAllRecipesService = get(),
          mapperRecipesRetrofitDataToModel = MapperRecipesRetrofitDataToModel(),

          recipesDao = get(),
          mapperRecipesRoomDataToModel = MapperRecipesRoomDataToModel(),
          mapperRecipesModelToRoomData = MapperRecipesModelToRoomData()
        )
      }

      single<GetRecipeRepository> {
        GetRecipeRepositoryImpl(
          roomDataSource = get(),

          recipesDao = get(),
          mapperRecipeRoomItemToModel = MapperRecipeRoomItemToModel(),
        )
      }
    })
  }
}