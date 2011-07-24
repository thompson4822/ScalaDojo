package com.videostore

import component.dao._
import component.service._
import org.scalatest.mock.MockitoSugar

/** Created on 7/23/11 at 11:08 AM by Steve */
 
trait TestingEnvironment extends MovieDaoComponent with CustomerDaoComponent with MovieInstanceDaoComponent with AccountDaoComponent  with ReviewDaoComponent with RentalDaoComponent
                        with InventoryServiceComponent with RentalServiceComponent with ReservationServiceComponent with CostServiceComponent with AccountServiceComponent with ReviewServiceComponent with CustomerServiceComponent
                        with LogServiceComponent with CommentDaoComponent with StorePolicyDaoComponent with LateFeeDaoComponent {
  this: MockitoSugar =>

  val movieDao = mock[MovieDao]
  val customerDao = mock[CustomerDao]
  val inventoryService = mock[InventoryService]
  val rentalService = mock[RentalService]
  val movieInstanceDao = mock[MovieInstanceDao]
  val reservationService = mock[ReservationService]
  val costService = mock[CostService]
  val accountService = mock[AccountService]
  val accountDao = mock[AccountDao]
  val reviewService = mock[ReviewService]
  val reviewDao = mock[ReviewDao]
  val customerService = mock[CustomerService]
  val rentalDao = mock[RentalDao]
  val logService = mock[LogService]
  val commentDao = mock[CommentDao]
  val storePolicyDao = mock[StorePolicyDao]
  val lateFeeDao = mock[LateFeeDao]
}