package com.videostore.component.dao

import com.videostore.model.{Customer, Movie, Reservation}
import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/23/11 at 1:02 PM by Steve */
 
trait IReservationDaoComponent {
  val reservationDao: IReservationDao

  trait IReservationDao {
    def save(reservation: Reservation): Unit
    def hasReservation(customer: Customer, movie: Movie): Boolean
  }
}

trait ReservationDaoComponent extends IReservationDaoComponent {
  class ReservationDao extends IReservationDao {
    def save(reservation: Reservation): Unit = throw new NotImplementedException
    def hasReservation(customer: Customer, movie: Movie): Boolean = throw new NotImplementedException
  }
}