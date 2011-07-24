package com.videostore.component.dao

/** Created on 7/23/11 at 1:02 PM by Steve */
 
trait IReservationDaoComponent {
  val reservationDao: IReservationDao

  trait IReservationDao {

  }
}

trait ReservationDaoComponent extends IReservationDaoComponent {
  class ReservationDao extends IReservationDao {

  }
}