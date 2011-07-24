package com.videostore.component.service

import com.videostore.model.{Reservation, Customer, Movie, MediaType}
import com.videostore.component.dao.IReservationDaoComponent
import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/23/11 at 12:58 PM by Steve */
 
trait IReservationServiceComponent {
  val reservationService: IReservationService

  trait IReservationService {
    def addReservation(customer: Customer, movie: Movie, mediaType: Option[MediaType.Value] = None): Unit
    def hasReservation(customer: Customer, movie: Movie): Boolean
    def removeFirstReservation(movie: Movie): Unit
    def removeReservationFor(movie: Movie, customer: Customer): Unit
    def nowAvailable(movie: Movie, mediaType: MediaType.Value): Unit
  }
}

// What movies are reserved and who's on the waiting list?
trait ReservationServiceComponent extends IReservationServiceComponent {
  this: IReservationDaoComponent =>

  class ReservationService extends IReservationService {
    def addReservation(customer: Customer, movie: Movie, mediaType: Option[MediaType.Value] = None): Unit = {
      if(reservationDao.hasReservation(customer, movie) == false)
        reservationDao.save(Reservation(0, customer, movie, mediaType))
    }

    def hasReservation(customer: Customer, movie: Movie): Boolean = throw new NotImplementedException
    def removeFirstReservation(movie: Movie): Unit = throw new NotImplementedException
    def removeReservationFor(movie: Movie, customer: Customer): Unit = throw new NotImplementedException

    // the movie has become available
    def nowAvailable(movie: Movie, mediaType: MediaType.Value): Unit = {
      // if there are any unfulfilled reservations for this movie/mediaType
      // set the whenNotified for the next person in line to now
      // send that person an email
      throw new NotImplementedException
    }
  }
}