package io.marleyspoonscodingchallenge.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// base content
@Serializable
internal data class RecipesRetrofitData(
  @SerialName("items") val items: List<Item>? = null,
  @SerialName("includes") val includes: Includes? = null
)

// items
@Serializable
internal data class Item(
  @SerialName("sys") val sys: SysItem? = null,
  @SerialName("fields") val fields: Fields? = null
)

@Serializable
internal data class SysItem(
  @SerialName("id") val id: String? = null
)

@Serializable
internal data class Fields(
  @SerialName("title") val title: String? = null,
  @SerialName("photo") val photo: Photo? = null,
  @SerialName("description") val description: String? = null,
  @SerialName("tags") val tags: List<Tag>? = null,
  @SerialName("chef") val chef: Chef? = null
)

@Serializable
internal data class Photo(
  @SerialName("sys") val sys: SysPhoto? = null
)

@Serializable
internal data class SysPhoto(
  @SerialName("id") val id: String? = null
)

@Serializable
internal data class Tag(
  @SerialName("sys") val sys: SysTag? = null
)

@Serializable
internal data class SysTag(
  @SerialName("id") val id: String? = null
)

@Serializable
internal data class Chef(
  @SerialName("sys") val sys: SysChef? = null
)

@Serializable
internal data class SysChef(
  @SerialName("id") val id: String? = null
)

// includes
@Serializable
internal data class Includes(
  @SerialName("Entry") val Entry: List<Entry>? = null,
  @SerialName("Asset") val Asset: List<Asset>? = null
)

// entry
@Serializable
internal data class Entry(
  @SerialName("sys") val sys: SysEntry? = null,
  @SerialName("fields") val fields: FieldsEntry? = null
)

@Serializable
internal data class SysEntry(
  @SerialName("id") val id: String? = null,
)

@Serializable
internal data class FieldsEntry(
  @SerialName("name") val name: String? = null
)

// asset
@Serializable
internal data class Asset(
  @SerialName("sys") val sys: SysAsset? = null,
  @SerialName("fields") val fields: FieldsAsset? = null
)

@Serializable
internal data class SysAsset(
  @SerialName("id") val id: String? = null,
)

@Serializable
internal data class FieldsAsset(
  @SerialName("file") val file: File? = null
)

@Serializable
internal data class File(
  @SerialName("url") val url: String? = null,
)