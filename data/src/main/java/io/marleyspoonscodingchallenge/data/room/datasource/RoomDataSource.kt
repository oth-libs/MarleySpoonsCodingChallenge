package io.marleyspoonscodingchallenge.data.room.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal object RoomDataSource {

  fun <MODEL_ROOM, MODEL> getRoomResult(
    call: () -> Flow<MODEL_ROOM>,
    transform: (MODEL_ROOM) -> MODEL,
  ): Flow<MODEL> {
    return call().map { transform(it) }
  }
}