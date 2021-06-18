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
  networkCall: (suspend () -> DataSourceResultHolder<MODEL>)? = null,
  insertResultQuery: (suspend (MODEL) -> Unit)? = null
): Flow<DataSourceResultHolder<MODEL>> {
  return flow {

    // notify the process started
    emit(DataSourceResultHolder.inProgress())

    // get cache
    var selectResponse = selectQuery().map { DataSourceResultHolder.success(it) }

    // get remote result, will also hold success status
    networkCall?.let {
      // send first cache if there is a network call
      emit(selectResponse.first())

      // call network
      val responseStatus = networkCall()

      if (responseStatus.status == DataSourceResultHolder.Status.SUCCESS) {

        // save data to room if successful
        insertResultQuery?.let { insertResultQuery(responseStatus.data!!) }

        // submit new local and listen for any future changes
        selectResponse = selectQuery().map { DataSourceResultHolder.success(it) }
        emitAll(selectResponse)
      } else {
        // let viewmodel handle errors
        emit(responseStatus)
      }
    } ?: run {
      // if there is no network call, submit local and listen for any future changes
      emitAll(selectResponse)
    }
  }
}
