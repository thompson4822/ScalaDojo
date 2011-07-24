package com.videostore.model

/** Created on 7/24/11 at 8:36 AM by Steve */

case class Movie(id: Long, title: String, year: Int, genres: List[Genre.Value] = List(Genre.UnknownGenre))
