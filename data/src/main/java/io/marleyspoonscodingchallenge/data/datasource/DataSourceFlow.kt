package io.marleyspoonscodingchallenge.data.datasource

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 *
 * [MODEL] - The model of the api result
 *
 * [networkCall] - Calls the network and implicitly checks for internet connection and returns the result wrapped in a [DataSourceResultHolder]
 *
 * Function notify UI about:
 * [DataSourceResultHolder.Status.SUCCESS] - with data from database
 * [DataSourceResultHolder.Status.ERROR] - if error has occurred`
 * [DataSourceResultHolder.Status.IN_PROGRESS]
 */
fun <MODEL> resultFlow(
  selectQuery: suspend () -> MODEL,
  networkCall: suspend () -> DataSourceResultHolder<MODEL>,
  insertResultQuery: suspend (MODEL) -> Unit
): Flow<DataSourceResultHolder<MODEL>> {
  return flow {

    // 1- notify the process started
    emit(DataSourceResultHolder.inProgress())

    // 2- get cache
//    val source = selectQuery().map {
//      DataSourceResultHolder.success(it)
//    }

//    val a  = withContext(Dispatchers.IO) { selectQuery() }
//    emit(DataSourceResultHolder.success(a))

    // 3- get remote result, will also hold success status
    val responseStatus = networkCall()

    when (responseStatus.status) {
      DataSourceResultHolder.Status.SUCCESS -> {
        insertResultQuery(responseStatus.data!!)
      }

      DataSourceResultHolder.Status.ERROR -> {
//        emit(responseStatus) // this emit will remove the source,
//        emitSource(source) // so we send it back again
      }

      DataSourceResultHolder.Status.NO_INTERNET -> {
//        // TODO fix. hack to wait for map to return live data before attempting remote call
//        delay(100)
//
//        // 4b- notify there was an error
//        emit(responseStatus.apply { successSource = if (hasCache) LOCAL else null })
      }

      else -> {
      }
    }

    emit(responseStatus)

  }
}

fun <MODEL> resultFlow(
  selectQuery: () -> Flow<MODEL>
): Flow<DataSourceResultHolder<MODEL>> {
  return flow {
    val a = withContext(Dispatchers.IO) { selectQuery() }

    emitAll(a.map { DataSourceResultHolder.success(it) })
  }
}

