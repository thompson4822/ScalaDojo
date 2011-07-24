package com.videostore.model

/** Created on 7/24/11 at 8:39 AM by Steve */

case class LateFee(id: Long, customer: Customer, rentals: Seq[Rental], totalCharge: Double)
