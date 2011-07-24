package com.videostore.component.service

/** Created on 7/23/11 at 12:58 PM by Steve */
 
trait IReservationServiceComponent {
  val reservationService: IReservationService

  trait IReservationService {

  }
}

// What movies are reserved and who's on the waiting list?
trait ReservationServiceComponent extends IReservationServiceComponent {
  class ReservationService extends IReservationService {

  }
}