package io.marleyspoonscodingchallenge.data.datasource

internal class RoomDataSource {

  fun <MODEL_ROOM, MODEL> getRoomResult(
    call: () -> MODEL_ROOM,
    transform: (MODEL_ROOM) -> MODEL,
  ): MODEL {

    return transform(call())
  }
}