package io.marleyspoonscodingchallenge.data.homepage.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// base content
@Serializable
data class RecipesData(
  @SerialName("items") val items: List<Item>? = null,
  @SerialName("includes") val includes: Includes? = null
)

// items
@Serializable
data class Item(
  @SerialName("sys") val sys: SysItem? = null,
  @SerialName("fields") val fields: Fields? = null
)

@Serializable
data class SysItem(
  @SerialName("id") val id: String? = null
)

@Serializable
data class Fields(
  @SerialName("title") val title: String? = null,
  @SerialName("photo") val photo: Photo? = null,
  @SerialName("description") val description: String? = null,
  @SerialName("tags") val tags: List<Tag>? = null,
  @SerialName("chef") val chef: Chef? = null
)

@Serializable
data class Photo(
  @SerialName("sys") val sys: SysPhoto? = null
)

@Serializable
data class SysPhoto(
  @SerialName("linkType") val linkType: String? = null,
  @SerialName("id") val id: String? = null
)

@Serializable
data class Tag(
  @SerialName("sys") val sys: SysTag? = null
)

@Serializable
data class SysTag(
  @SerialName("linkType") val linkType: String? = null,
  @SerialName("id") val id: String? = null
)

@Serializable
data class Chef(
  @SerialName("sys") val sys: SysChef? = null
)

@Serializable
data class SysChef(
  @SerialName("linkType") val linkType: String? = null,
  @SerialName("id") val id: String? = null
)

// includes
@Serializable
data class Includes(
  @SerialName("Entry") val Entry: List<Entry>? = null,
  @SerialName("Asset") val Asset: List<Asset>? = null
)

// entry
@Serializable
data class Entry(
  @SerialName("sys") val sys: SysEntry? = null,
  @SerialName("fields") val fields: FieldsX? = null
)

@Serializable
data class SysEntry(
  @SerialName("id") val id: String? = null,
)

@Serializable
data class FieldsX(
  @SerialName("name") val name: String? = null
)

// asset
@Serializable
data class Asset(
  @SerialName("sys") val sys: SysAsset? = null,
  @SerialName("fields") val fields: FieldsAsset? = null
)

@Serializable
data class SysAsset(
  @SerialName("id") val id: String? = null,
)

@Serializable
data class FieldsAsset(
  @SerialName("file") val file: File? = null
)

@Serializable
data class File(
  @SerialName("url") val url: String? = null,
)