package io.marleyspoonscodingchallenge.data.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RoomDataSource {

  fun <MODEL_ROOM, MODEL> getRoomResult(
    call: () -> MODEL_ROOM,
    transform: (MODEL_ROOM) -> MODEL,
  ): MODEL {

    return transform(call())
  }


  fun <MODEL_ROOM, MODEL> getRoomResult2(
    call: () -> Flow<MODEL_ROOM>,
    transform: (MODEL_ROOM) -> MODEL,
  ): Flow<MODEL> {
    return call().map { transform(it) }
  }
}