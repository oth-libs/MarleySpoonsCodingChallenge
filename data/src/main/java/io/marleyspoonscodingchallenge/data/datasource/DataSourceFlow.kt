package io.marleyspoonscodingchallenge.data.datasource

import io.marleyspoonscodingchallenge.domain.datasource.DataSourceResultHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
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
  selectQuery: suspend () -> Flow<MODEL>,
  networkCall: suspend () -> DataSourceResultHolder<MODEL>,
  insertResultQuery: suspend (MODEL) -> Unit
): Flow<DataSourceResultHolder<MODEL>> {
  return flow {

    // notify the process started
    emit(DataSourceResultHolder.inProgress())

    // get cache
    val selectResponseFirst = selectQuery().map { DataSourceResultHolder.success(it) }.first()
    emit(selectResponseFirst)

    // get remote result, will also hold success status
    val responseStatus = networkCall()

    if (responseStatus.status == DataSourceResultHolder.Status.SUCCESS) {
      insertResultQuery(responseStatus.data!!)

      // submit new local and listen for any future changes
      val selectResponseFirstAll = selectQuery().map { DataSourceResultHolder.success(it) }
      emitAll(selectResponseFirstAll)
    } else {
      // let viewmodel handle errors
      emit(responseStatus)
    }
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

