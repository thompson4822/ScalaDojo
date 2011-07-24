package com.videostore.model

import java.awt.PageAttributes.MediaType
import java.util.Date

/** Created on 7/24/11 at 12:26 PM by Steve */
 
case class Reservation(id: Long, customer: Customer, movie: Movie, mediaType: Option[MediaType.Value] = None, whenNotified: Option[Date] = None)