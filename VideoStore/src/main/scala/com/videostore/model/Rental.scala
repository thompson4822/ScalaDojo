package com.videostore.model

import java.util.Date

/** Created on 7/24/11 at 8:38 AM by Steve */

case class Rental(id: Long, movie: MovieInstance, customer: Customer, charge: BigDecimal, rentalDate: Date = new Date, var returnDate: Option[Date] = None)
