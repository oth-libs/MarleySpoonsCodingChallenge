package io.marleyspoonscodingchallenge.domain.datasource

sealed class DataSourceException : Throwable()

class NoInternetException : DataSourceException()

class ErrorException : DataSourceException()