package io.marleyspoonscodingchallenge.data.di

import io.marleyspoonscodingchallenge.data.api.RetrofitFactory
import io.marleyspoonscodingchallenge.data.homepage.api.GetAllRecipesService
import io.marleyspoonscodingchallenge.data.homepage.mapper.MapperRecipesDataToModel
import io.marleyspoonscodingchallenge.data.homepage.model.RecipesData
import io.marleyspoonscodingchallenge.data.homepage.repository.GetAllRecipesRepositoryImpl
import io.marleyspoonscodingchallenge.data.mapper.Mapper
import io.marleyspoonscodingchallenge.domain.homepage.model.RecipesModel
import io.marleyspoonscodingchallenge.domain.homepage.repository.GetAllRecipesRepository
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

      //  mappers
      single<Mapper<RecipesData, RecipesModel>> { MapperRecipesDataToModel() }

      // repository
      single<GetAllRecipesRepository> { GetAllRecipesRepositoryImpl(getAllRecipesService = get(), mapper = get()) }
    })
  }
}