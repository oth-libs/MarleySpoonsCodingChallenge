package io.marleyspoonscodingchallenge.data.homepage.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RecipesData(
  @SerialName("items") val items: List<Item>? = null,
  @SerialName("includes") val includes: Includes? = null
)

@Serializable
internal data class Item(
  @SerialName("sys") val sys: Sys? = null,
  @SerialName("fields") val fields: Fields? = null
)

@Serializable
internal data class Sys(
  @SerialName("id") val id: String? = null,
  @SerialName("contentType") val contentType: ContentType? = null
)

@Serializable
internal data class ContentType(
  @SerialName("sys") val sys: SysContentType? = null
)

@Serializable
internal data class SysContentType(
  @SerialName("type") val type: String? = null,
  @SerialName("linkType") val linkType: String? = null,
  @SerialName("id") val id: String? = null
)

@Serializable
internal data class Fields(
  @SerialName("title") val title: String? = null,
  @SerialName("photo") val photo: Photo? = null
)

@Serializable
internal data class Photo(
  @SerialName("sys") val sys: SysPhoto? = null
)

@Serializable
internal data class SysPhoto(
  @SerialName("type") val type: String? = null,
  @SerialName("linkType") val linkType: String? = null,
  @SerialName("id") val id: String? = null
)

@Serializable
internal data class Includes(
  @SerialName("Asset") val Asset: List<Asset>? = null
)

@Serializable
internal data class Asset(
  @SerialName("sys") val sys: SysAsset? = null,
  @SerialName("fields") val fields: AssetFields? = null
)

@Serializable
data class SysAsset(
  @SerialName("id") val id: String? = null,
)

@Serializable
internal data class AssetFields(
  @SerialName("file") val file: File? = null
)

@Serializable
internal data class File(
  @SerialName("url") val url: String? = null,
)