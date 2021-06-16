package io.marleyspoonscodingchallenge

import android.app.Application
import com.doublesymetrymusic.presentation.di.PresentationModule
import io.marleyspoonscodingchallenge.data.di.DataModule
import io.marleyspoonscodingchallenge.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class MSApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@MSApplication)
    }

    loadModules()
  }

  private fun loadModules() {
    PresentationModule.load()
    DomainModule.load()
    DataModule.load()
  }
}
