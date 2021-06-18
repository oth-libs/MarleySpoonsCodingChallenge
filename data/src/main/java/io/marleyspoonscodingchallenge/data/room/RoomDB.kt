package io.marleyspoonscodingchallenge.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.marleyspoonscodingchallenge.data.room.converter.RecipesConverter
import io.marleyspoonscodingchallenge.data.room.dao.RecipesDao
import io.marleyspoonscodingchallenge.data.room.model.RecipeRoomItem

@Database(
  entities = [
    RecipeRoomItem::class
  ],
  version = 1
)
@TypeConverters(
  RecipesConverter::class
)
internal abstract class RoomDB : RoomDatabase() {

  abstract fun recipesDao(): RecipesDao

  companion object {
    private var instance: RoomDB? = null

    fun getInstance(context: Context): RoomDB {
      if (instance == null) {
        synchronized(RoomDB::class) {
          instance = createInstance(context)
            .build()
        }
      }
      return instance!!
    }

    private fun createInstance(context: Context) = Room.databaseBuilder(context.applicationContext, RoomDB::class.java, DB_PROSPERITY)
  }

}