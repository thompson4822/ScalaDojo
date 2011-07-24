package com.videostore.model

//import com.videostore.model.enumeration.MediaType
import java.util.Date

/** Created on 7/24/11 at 8:37 AM by Steve */

case class MovieInstance(id: Long, movie: Movie, acquisitionDate: Date, mediaType: MediaType.Value, originalCost: Double)

