package com.videostore.model

import java.util.Date

/** Created on 7/24/11 at 8:39 AM by Steve */

case class LateFee(id: Long, rental: Rental, daysLate: Int, var paidOn: Option[Date])
