package io.marleyspoonscodingchallenge.data.mapper

internal interface Mapper<FROM, TO> {
  fun map(from: FROM): TO
}