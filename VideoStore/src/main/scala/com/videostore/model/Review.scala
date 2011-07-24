package com.videostore.model

import java.util.Date

/** Created on 7/24/11 at 8:39 AM by Steve */

case class Review(id: Long, account: Account, movie: Movie, var rating: Double, var title: String = "", var text: String = "", ratingDate: Date = new Date)

