package io.marleyspoonscodingchallenge.data.room.converter

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RecipesConverter {

  @TypeConverter
  fun fromObject(data: List<String>?): String? {
    data ?: return null

    return Json.encodeToString(data)
  }

  @TypeConverter
  fun toObject(string: String?): List<String>? {
    string ?: return null

    return Json.decodeFromString(string)
  }

}