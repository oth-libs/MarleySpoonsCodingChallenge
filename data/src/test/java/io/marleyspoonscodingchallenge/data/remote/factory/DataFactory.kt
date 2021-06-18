package io.marleyspoonscodingchallenge.data.remote.factory

import io.marleyspoonscodingchallenge.data.remote.model.Asset
import io.marleyspoonscodingchallenge.data.remote.model.Chef
import io.marleyspoonscodingchallenge.data.remote.model.Entry
import io.marleyspoonscodingchallenge.data.remote.model.Fields
import io.marleyspoonscodingchallenge.data.remote.model.FieldsAsset
import io.marleyspoonscodingchallenge.data.remote.model.FieldsEntry
import io.marleyspoonscodingchallenge.data.remote.model.File
import io.marleyspoonscodingchallenge.data.remote.model.Includes
import io.marleyspoonscodingchallenge.data.remote.model.Item
import io.marleyspoonscodingchallenge.data.remote.model.Photo
import io.marleyspoonscodingchallenge.data.remote.model.RecipesRetrofitData
import io.marleyspoonscodingchallenge.data.remote.model.SysAsset
import io.marleyspoonscodingchallenge.data.remote.model.SysChef
import io.marleyspoonscodingchallenge.data.remote.model.SysEntry
import io.marleyspoonscodingchallenge.data.remote.model.SysItem
import io.marleyspoonscodingchallenge.data.remote.model.SysPhoto
import io.marleyspoonscodingchallenge.data.remote.model.SysTag
import io.marleyspoonscodingchallenge.data.remote.model.Tag
import io.marleyspoonscodingchallenge.domain.model.RecipeItem
import io.marleyspoonscodingchallenge.domain.model.RecipesModel

internal object DataFactory {

  val recipesRetrofitData = RecipesRetrofitData(
    items = listOf(
      Item(
        sys = SysItem(id = "id123"),
        fields = Fields(
          title = "title123",
          photo = Photo(
            sys = SysPhoto(
              id = "photoid123"
            )
          ),
          description = "description123",
          tags = listOf(
            Tag(
              sys = SysTag(
                id = "tagid123"
              )
            )
          ),
          chef = Chef(
            sys = SysChef(
              id = "chefid123"
            )
          )
        )
      )
    ),
    includes = Includes(
      Entry = listOf(
        Entry(
          sys = SysEntry(
            id = "tagid123"
          ),
          fields = FieldsEntry(
            name = "tag1"
          )
        ),
        Entry(
          sys = SysEntry(
            id = "chefid123"
          ),
          fields = FieldsEntry(
            name = "chef123"
          )
        )
      ),
      Asset = listOf(
        Asset(
          sys = SysAsset(
            id = "photoid123",
          ),
          fields = FieldsAsset(
            file = File(
              url = "url123"
            )
          )
        )
      )
    )
  )

  val recipesModel = RecipesModel(
    recipes = listOf(
      RecipeItem(
        id = "id123",
        title = "title123",
        photoUrl = "url123",
        tags = listOf("tag1"),
        description = "description123",
        chefName = "chef123"
      )
    )
  )
}